package com.main.backend.features.user.api.verification;

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

    @Autowired
    public VerificationTokenService(VerificationTokenRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public String registration(String username, String password, String email) {
        UserEntity user = userService.createUser(username, password, email);
        final String token = generateNewToken(user, REGISTRATION);

        return "OK" + token; // todo
    }

    public String confirmRegistration(String token) {
        return "OK"; // todo
    }

    public String forgotPassword(String userId) throws UserNotFountException {
        UserEntity user = userService.getUserEntity(userId);

        if (user == null) {
            log.error("User: {} not found", userId);
            throw new UserNotFountException("User not found");
        }
        final String token = generateNewToken(user, FORGET_PASSWORD);

        return "OK" + token; // todo
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
