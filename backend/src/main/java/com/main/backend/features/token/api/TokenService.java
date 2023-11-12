package com.main.backend.features.token.api;

import com.main.backend.features.mailserver.EmailService;
import com.main.backend.features.token.domain.TokenType;
import com.main.backend.features.token.exception.TokenHasNoUserException;
import com.main.backend.features.token.exception.TokenHasWrongTypeException;
import com.main.backend.features.token.exception.TokenNotFoundException;
import com.main.backend.features.user.api.UserService;
import com.main.backend.features.user.entity.UserEntity;
import com.main.backend.features.token.entity.TokenEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.main.backend.features.token.domain.TokenType.REGISTRATION;
import static com.main.backend.features.token.domain.TokenType.FORGET_PASSWORD;

@Slf4j
@Service
public class TokenService {
    private final TokenRepository repository;
    private final EmailService emailService;
    private final UserService userService;

    @Autowired
    public TokenService(TokenRepository repository, EmailService emailService, UserService userService) {
        this.repository = repository;
        this.emailService = emailService;
        this.userService = userService;
    }

    public String registration(String username, String password, String email) {
        final UserEntity user = userService.createUser(username, password, email);
        final String token = generateNewToken(user, REGISTRATION);
        final String body = String.format("To confirm registration go here %s", token); // todo link to proper website

        emailService.sendEmail(user.getEmail(), "Confirm registration", body);
        return "New account created, confirm email before log in";
    }

    public String confirmRegistration(String token) throws Exception {
        final UserEntity user = getUserOfTokenAndCheckTokenType(token, REGISTRATION);
        userService.confirmRegistration(user);
        return "Email confirmed successfully";
    }

    public String forgotPassword(String email) throws Exception {
        final UserEntity user = userService.getUserByMail(email);
        final String token = generateNewToken(user, FORGET_PASSWORD);
        final String body = String.format("To confirm reset of password go here %s", token); // todo link to proper website

        emailService.sendEmail(user.getEmail(), "Reset of password", body);
        return "Email with reset password instructions has been sent";
    }

    public String resetPassword(String token) throws Exception {
        final UserEntity user = getUserOfTokenAndCheckTokenType(token, FORGET_PASSWORD);
        final String newPassword = userService.resetPassword(user);

        emailService.sendEmail(user.getEmail(), "Regain access", String.format("Your new password: %s.", newPassword));
        return "Password reset successfully. Check email to get new password";
    }

    private String generateNewToken(UserEntity user, TokenType tokenType) {
        final String token = String.valueOf(UUID.randomUUID());
        TokenEntity tokenEntity = TokenEntity.builder()
                .token(token)
                .user(user)
                .tokenType(tokenType)
                .build();
        repository.saveAndFlush(tokenEntity);

        return token;
    }

    private UserEntity getUserOfTokenAndCheckTokenType(String token, TokenType tokenType) throws Exception {
        if (!repository.existsById(token))
            throw new TokenNotFoundException();

        TokenEntity tokenEntity = repository.getReferenceById(token);
        if (!tokenEntity.getTokenType().equals(tokenType))
            throw new TokenHasWrongTypeException();

        UserEntity user = tokenEntity.getUser();
        if (user == null)
            throw new TokenHasNoUserException();

        return user;
    }
}
