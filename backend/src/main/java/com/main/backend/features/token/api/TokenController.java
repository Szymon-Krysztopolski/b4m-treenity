package com.main.backend.features.token.api;

import com.main.backend.features.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1")
public class TokenController {
    private final TokenService service;

    @Autowired
    public TokenController(TokenService service) {
        this.service = service;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody UserDTO userDTO) {
        HttpStatus status;
        String response;

        try {
            status = HttpStatus.OK;
            response = service.registration(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
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
        HttpStatus status;
        String response;

        try {
            status = HttpStatus.OK;
            response = service.confirmRegistration(token);
            log.info("Account confirmed successfully");
        } catch (Exception ex) {
            status = HttpStatus.BAD_REQUEST;
            response = "Error during account confirmation!";
            log.error(response, ex);
        }

        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody String userId) {
        HttpStatus status;
        String response;

        try {
            status = HttpStatus.OK;
            response = service.forgotPassword(userId);
            log.info("Forget-password token created successfully");
        } catch (Exception ex) {
            status = HttpStatus.BAD_REQUEST;
            response = "Error during token creation!";
            log.error(response, ex);
        }

        return ResponseEntity.status(status).body(response);
    }
}
