package com.main.backend.features.token.api;

import com.main.backend.features.token.domain.TokenType;
import com.main.backend.features.token.entity.TokenEntity;
import com.main.backend.features.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, String> {
    @Transactional
    void deleteAllByUserAndTokenType(UserEntity user, TokenType type);
}
