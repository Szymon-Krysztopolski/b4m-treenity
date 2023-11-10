package com.main.backend.features.token.api;

import com.main.backend.features.mailserver.EmailService;
import com.main.backend.features.token.domain.TokenType;
import com.main.backend.features.user.entity.UserEntity;
import com.main.backend.features.token.entity.TokenEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.main.backend.features.token.domain.TokenType.REGISTRATION;
import static com.main.backend.features.token.domain.TokenType.FORGET_PASSWORD;

@Service
@Slf4j
public class TokenService {
    private final TokenRepository repository;
    private final EmailService emailService;

    @Autowired
    public TokenService(TokenRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public String registration(UserEntity user) {
        final String token = generateNewToken(user, REGISTRATION);
        final String body = String.format("To confirm registration go here %s", token); // todo link to proper website

        emailService.sendEmail(user.getEmail(), "Confirm registration", body);
        return "New account created, confirm email before log in";
    }

    public String confirmRegistration(String token) {
        TokenEntity tokenENtity = repository.findByToken(token);
        return "OK"; // todo
    }

    public String forgotPassword(UserEntity user) {
        final String token = generateNewToken(user, FORGET_PASSWORD);
        final String body = String.format("To confirm reset of password go here %s", token); // todo link to proper website

        emailService.sendEmail(user.getEmail(), "Reset of password", body);
        return "Email with reset password instructions has been sent";
    }

    private String generateNewToken(UserEntity user, TokenType tokenType) {
        String token = String.valueOf(UUID.randomUUID());
        TokenEntity tokenEntity = TokenEntity.builder()
                .token(token)
                .user(user)
                .tokenType(tokenType)
                .build();
        repository.saveAndFlush(tokenEntity);

        return token;
    }
}
