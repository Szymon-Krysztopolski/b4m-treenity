package com.main.backend.features.user.api;

import com.main.backend.features.user.domain.User;
import com.main.backend.features.user.entity.UserEntity;
import com.main.backend.features.user.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers() {
        final List<UserEntity> entities = repository.findAll();

        return entities.stream()
                .map(User::from)
                .collect(Collectors.toList());
    }

    public UserEntity createUser(String username, String password, String email) {
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

    public UserEntity getUserByMail(String email) throws UserNotFoundException {
        UserEntity user = repository.findByEmail(email);
        if (user == null)
            throw new UserNotFoundException();

        return user;
    }

    public void confirmRegistration(@NotNull UserEntity user) {
        user.setIsActive(true);
        repository.saveAndFlush(user);
    }

    public String resetPassword(@NotNull UserEntity user) {
        String newPassword = String.valueOf(UUID.randomUUID()).replace("-", "");

        user.setPasswordHash(newPassword); // todo set passwordHash
        log.info(newPassword); // todo del
        repository.saveAndFlush(user);
        return newPassword;
    }
}
