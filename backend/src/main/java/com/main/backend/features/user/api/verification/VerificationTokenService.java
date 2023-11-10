package com.main.backend.features.user.api.verification;

import com.main.backend.features.mailserver.EmailService;
import com.main.backend.features.user.api.userdata.UserService;
import com.main.backend.features.user.domain.VerificationTokenType;
import com.main.backend.features.user.entity.UserEntity;
import com.main.backend.features.user.entity.VerificationTokenEntity;
import com.main.backend.features.user.exception.UserNotFountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.main.backend.features.user.domain.VerificationTokenType.REGISTRATION;
import static com.main.backend.features.user.domain.VerificationTokenType.FORGET_PASSWORD;

@Service
@Slf4j
public class VerificationTokenService {
    private final VerificationTokenRepository repository;
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public VerificationTokenService(VerificationTokenRepository repository, UserService userService, EmailService emailService) {
        this.repository = repository;
        this.userService = userService;
        this.emailService = emailService;
    }

    public String registration(String username, String password, String email) {
        final UserEntity user = userService.createUser(username, password, email);
        final String token = generateNewToken(user, REGISTRATION);
        final String body = String.format("To confirm registration go here %s", token); // todo link to proper website

        emailService.sendEmail(email, "Confirm registration", body);
        return "New account created, confirm email before log in";
    }

    public String confirmRegistration(String token) {
        return "OK"; // todo
    }

    public String forgotPassword(String userId) throws UserNotFountException {
        final UserEntity user = userService.getUserEntity(userId);
        if (user == null) {
            log.error("User: {} not found", userId);
            throw new UserNotFountException("User not found");
        }
        final String token = generateNewToken(user, FORGET_PASSWORD);
        final String body = String.format("To confirm reset of password go here %s", token); // todo link to proper website

        emailService.sendEmail(user.getEmail(), "Reset of password", body);
        return "Email with reset password instructions has been sent";
    }

    private String generateNewToken(UserEntity user, VerificationTokenType tokenType) {
        String token = String.valueOf(UUID.randomUUID());
        VerificationTokenEntity tokenEntity = VerificationTokenEntity.builder()
                .token(token)
                .user(user)
                .tokenType(tokenType)
                .build();
        repository.saveAndFlush(tokenEntity);

        return token;
    }
}
