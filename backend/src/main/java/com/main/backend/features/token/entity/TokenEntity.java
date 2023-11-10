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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private UserEntity user;

    @Column(unique = true)
    private String token;
    private TokenType tokenType;
    private Boolean isUsed = false;

    // Each token expires after 30 minutes
    private Date expiryDate = Date.from(Instant.now().plusSeconds(60 * 30));
}
