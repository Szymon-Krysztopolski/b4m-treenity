package com.main.backend.features.user.api.verification;

import com.main.backend.features.user.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {
}
