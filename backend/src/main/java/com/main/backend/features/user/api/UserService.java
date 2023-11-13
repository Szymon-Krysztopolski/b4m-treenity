package com.main.backend.features.user.api;

import com.main.backend.features.user.domain.User;
import com.main.backend.features.user.entity.UserEntity;
import com.main.backend.features.user.exception.BlankFieldException;
import com.main.backend.features.user.exception.InvalidEmailException;
import com.main.backend.features.user.exception.UserNotFoundException;
import com.main.backend.features.user.exception.WrongPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        final List<UserEntity> entities = repository.findAll();

        return entities.stream()
                .map(User::from)
                .collect(Collectors.toList());
    }

    public UserEntity createUser(String username, String password, String email) throws Exception {
        if (!checkIfBlank(username, password, email))
            throw new BlankFieldException();

        if (!EmailValidator.getInstance().isValid(email))
            throw new InvalidEmailException();

        String newUserId = String.valueOf(UUID.randomUUID());
        UserEntity newUser = UserEntity.builder()
                .id(newUserId)
                .username(username)
                .passwordHash(passwordEncoder.encode(password))
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

    public UserEntity checkPassword(String email, String password) throws UserNotFoundException, WrongPasswordException {
        UserEntity user = getUserByMail(email);
        if (!passwordEncoder.matches(password, user.getPasswordHash()))
            throw new WrongPasswordException();

        return user;
    }

    public void confirmRegistration(@NotNull UserEntity user) {
        user.setIsActive(true);
        repository.saveAndFlush(user);
    }

    public String resetPassword(@NotNull UserEntity user) {
        String newPassword = String.valueOf(UUID.randomUUID()).replace("-", "");

        user.setPasswordHash(passwordEncoder.encode(newPassword));
        repository.saveAndFlush(user);
        return newPassword;
    }

    private boolean checkIfBlank(String... args) {
        for (String arg : args) {
            if (arg == null || arg.trim().isBlank())
                return false;
        }
        return true;
    }
}
