package com.main.backend.features.user.entity;

import com.main.backend.features.user.domain.VerificationTokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "verification_codes")
public class VerificationTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private UserEntity user;
    private String token;
    private VerificationTokenType tokenType;
    private Boolean isUsed = false;

    // Each token expires after 30 minutes
    private Date expiryDate = Date.from(Instant.now().plusSeconds(60 * 30));
}
