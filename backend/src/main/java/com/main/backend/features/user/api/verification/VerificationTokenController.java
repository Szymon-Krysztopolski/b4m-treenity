package com.main.backend.features.user.api.verification;

import com.main.backend.features.user.dto.UserDTO;
import com.main.backend.features.user.exception.UserNotFountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class VerificationTokenController {
    private final VerificationTokenService service;

    @Autowired
    public VerificationTokenController(VerificationTokenService service) {
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
        return ResponseEntity.ok(service.confirmRegistration(token));
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
