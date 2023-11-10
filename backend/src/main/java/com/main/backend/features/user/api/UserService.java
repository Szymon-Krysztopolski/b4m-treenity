package com.main.backend.features.user.api;

import com.main.backend.features.token.api.TokenService;
import com.main.backend.features.user.domain.User;
import com.main.backend.features.user.entity.UserEntity;
import com.main.backend.features.user.exception.UserNotFountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    private final UserRepository repository;
    private final TokenService tokenService;

    @Autowired
    public UserService(UserRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    public List<User> getUsers() {
        final List<UserEntity> entities = repository.findAll();

        return entities.stream()
                .map(User::from)
                .collect(Collectors.toList());
    }

    public String registration(String username, String password, String email) {
        final UserEntity user = createUser(username, password, email);
        return tokenService.registration(user);
    }

    public String confirmRegistration(String token) {
        return tokenService.confirmRegistration(token);
    }

    public String forgotPassword(String userId) throws UserNotFountException {
        final UserEntity user = repository.getReferenceById(userId);
        return tokenService.forgotPassword(user);
    }

    private UserEntity createUser(String username, String password, String email) {
        // todo check if email if proper and doesn't exist in database

        String newUserId = String.valueOf(UUID.randomUUID());
        UserEntity newUser = UserEntity.builder()
                .id(newUserId)
                .username(username)
                .passwordHash(password) // todo hash
                .email(email)
                .build();
        repository.saveAndFlush(newUser);

        log.info("New user: {} has been created", newUserId);
        return newUser;
    }
}
