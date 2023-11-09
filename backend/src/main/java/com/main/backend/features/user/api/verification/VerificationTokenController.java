package com.main.backend.features.user.api.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VerificationTokenController {
    private final VerificationTokenService service;

    @Autowired
    public VerificationTokenController(VerificationTokenService service) {
        this.service = service;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration() {
        return ResponseEntity.ok(service.registration());
    }

    @GetMapping("/registration/{token}")
    public ResponseEntity<String> confirmRegistration(@PathVariable String token) {
        return ResponseEntity.ok(service.confirmRegistration());
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword() {
        return ResponseEntity.ok(service.forgotPassword());
    }
}
