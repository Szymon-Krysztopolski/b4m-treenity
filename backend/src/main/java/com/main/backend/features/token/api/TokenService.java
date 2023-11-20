package com.main.backend.features.token.api;

import com.main.backend.features.mailserver.EmailService;
import com.main.backend.features.token.domain.TokenUtils;
import com.main.backend.features.token.exception.EmptyMailLinkException;
import com.main.backend.features.user.api.UserService;
import com.main.backend.features.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.main.backend.features.token.domain.TokenType.*;

@Slf4j
@Service
public class TokenService {
    private final TokenRepository repository;
    private final EmailService emailService;
    private final UserService userService;
    private final TokenUtils utils;

    @Value("${tri.token.mail.link}")
    private String mailLink;

    @Autowired
    public TokenService(TokenRepository repository, EmailService emailService, UserService userService, TokenUtils utils) {
        this.repository = repository;
        this.emailService = emailService;
        this.userService = userService;
        this.utils = utils;
    }

    public String login(String email, String password) throws Exception {
        final UserEntity user = userService.checkPasswordByMail(email, password);
        repository.deleteAllByUserAndTokenType(user, SESSION);
        return utils.generateNewToken(repository, user, SESSION);
    }

    public String logout(String token) {
        repository.deleteById(token);
        return "Session logout successfully";
    }

    public String changePassword(String token, String currentPassword, String newPassword) throws Exception {
        final UserEntity user = checkTokenAndGetUser(token);
        userService.changePassword(user, currentPassword, newPassword);

        return "Password changed successfully";
    }

    public String registration(String username, String password, String email) throws Exception {
        if (mailLink == null || mailLink.trim().isBlank())
            throw new EmptyMailLinkException();

        final UserEntity user = userService.createUser(username, password, email);
        final String token = utils.generateNewToken(repository, user, REGISTRATION);
        final String body = String.format("To confirm registration go here: %s/v1/registration/%s", mailLink, token);

        emailService.sendEmail(user.getEmail(), "Confirm registration", body);
        return "New account created, confirm email before log in";
    }

    public String confirmRegistration(String token) throws Exception {
        final UserEntity user = utils.getUserOfTokenAndCheckTokenType(repository, token, REGISTRATION);
        userService.confirmRegistration(user);
        return "Email confirmed successfully";
    }

    public String forgotPassword(String email) throws Exception {
        if (mailLink == null || mailLink.trim().isBlank())
            throw new EmptyMailLinkException();

        final UserEntity user = userService.getUserByMail(email);
        final String token = utils.generateNewToken(repository, user, FORGET_PASSWORD);
        final String body = String.format("To confirm reset of password go here: %s/v1/forgot-password/%s", mailLink, token);

        emailService.sendEmail(user.getEmail(), "Reset of password", body);
        return "Email with reset password instructions has been sent";
    }

    public String resetPassword(String token) throws Exception {
        final UserEntity user = utils.getUserOfTokenAndCheckTokenType(repository, token, FORGET_PASSWORD);
        final String newPassword = userService.resetPassword(user);

        return String.format("Password reset successfully. Your new password: %s", newPassword);
    }

    public UserEntity checkTokenAndGetUser(String sessionToken) throws Exception {
        return utils.getUserOfTokenAndCheckTokenType(repository, sessionToken, SESSION);
    }
}
