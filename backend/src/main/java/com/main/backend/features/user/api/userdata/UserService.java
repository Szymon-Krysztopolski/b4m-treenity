package com.main.backend.features.user.api.userdata;

import com.main.backend.features.user.domain.User;
import com.main.backend.features.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        final List<UserEntity> entities = repository.findAll();

        return entities.stream()
                .map(User::from)
                .collect(Collectors.toList());
    }
}
