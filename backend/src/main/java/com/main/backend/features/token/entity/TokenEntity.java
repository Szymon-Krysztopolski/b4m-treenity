package com.main.backend.features.token.entity;

import com.main.backend.features.token.domain.TokenType;
import com.main.backend.features.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

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

    @Setter
    private Boolean isUsed;
    private Date expiryDate;

    @PrePersist
    public void prePersist() {
        isUsed = false;

        // Each token expires after 15 minutes
        expiryDate = Date.from(Instant.now().plusSeconds(60 * 15));
    }

    public boolean isExpired() {
        return Date.from(Instant.now()).after(expiryDate);
    }
}
