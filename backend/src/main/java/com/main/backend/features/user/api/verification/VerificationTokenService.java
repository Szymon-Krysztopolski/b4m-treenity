package com.main.backend.features.user.api.verification;

import com.main.backend.features.user.domain.VerificationTokenType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VerificationTokenService {
    private final VerificationTokenRepository repository;

    @Autowired
    public VerificationTokenService(VerificationTokenRepository repository) {
        this.repository = repository;
    }

    public String registration() {
        return "OK"; // todo
    }

    public String confirmRegistration() {
        return "OK"; // todo
    }

    public String forgotPassword() {
        return "OK"; // todo
    }

    private String generateNewToken(VerificationTokenType tokenType) {
        return "new token"; // todo
    }
}
