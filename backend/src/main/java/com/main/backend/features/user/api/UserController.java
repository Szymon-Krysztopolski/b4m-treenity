package com.main.backend.features.user.api;

import com.main.backend.features.token.api.TokenService;
import com.main.backend.features.user.domain.User;
import com.main.backend.features.user.dto.UserDTO;
import com.main.backend.features.user.entity.UserEntity;
import com.main.backend.features.user.exception.UserNotFountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody UserDTO userDTO) {
        HttpStatus status;
        String response;

        try {
            final UserEntity user = userService.createUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
            status = HttpStatus.OK;
            response = tokenService.registration(user);

            log.info("Registration token created successfully");
        } catch (Exception ex) {
            status = HttpStatus.BAD_REQUEST;
            response = "Error during token creation!";

            log.error(response, ex);
        }

        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/registration/{token}")
    public ResponseEntity<String> confirmRegistration(@PathVariable String token) {
        return ResponseEntity.ok(tokenService.confirmRegistration(token));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody String userId) {
        HttpStatus status;
        String response;

        try {
            final UserEntity user = userService.getUserEntity(userId);
            if (user == null) {
                log.error("User: {} not found", userId);
                throw new UserNotFountException("User not found");
            }

            status = HttpStatus.OK;
            response = tokenService.forgotPassword(user);

            log.info("Forget-password token created successfully");
        } catch (Exception ex) {
            status = HttpStatus.BAD_REQUEST;
            response = "Error during token creation!";

            log.error(response, ex);
        }

        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
