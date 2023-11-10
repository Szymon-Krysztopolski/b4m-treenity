package com.main.backend.features.user.api;

import com.main.backend.features.user.domain.User;
import com.main.backend.features.user.entity.UserEntity;
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

    public UserEntity getUserEntity(String id) {
        return repository.getReferenceById(id);
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
}
