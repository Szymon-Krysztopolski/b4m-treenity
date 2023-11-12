package com.main.backend.features.token.entity;

import com.main.backend.features.token.domain.TokenType;
import com.main.backend.features.user.entity.UserEntity;
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
@Table(name = "tokens")
public class TokenEntity {
    @Id
    private String token;

    @ManyToOne
    private UserEntity user;
    private TokenType tokenType;
    private Boolean isUsed = false;

    // Each token expires after 15 minutes
    private Date expiryDate = Date.from(Instant.now().plusSeconds(60 * 15));
}
