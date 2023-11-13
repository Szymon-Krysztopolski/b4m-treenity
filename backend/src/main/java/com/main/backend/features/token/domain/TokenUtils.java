package com.main.backend.features.token.domain;

import com.main.backend.features.token.api.TokenRepository;
import com.main.backend.features.token.entity.TokenEntity;
import com.main.backend.features.token.exception.*;
import com.main.backend.features.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenUtils {

    public String generateNewToken(TokenRepository repository, UserEntity user, TokenType tokenType) {
        final String token = String.valueOf(UUID.randomUUID());
        TokenEntity tokenEntity = TokenEntity.builder()
                .token(token)
                .user(user)
                .tokenType(tokenType)
                .build();
        repository.saveAndFlush(tokenEntity);

        return token;
    }

    public UserEntity getUserOfTokenAndCheckTokenType(TokenRepository repository, String token, TokenType tokenType) throws Exception {
        if (!repository.existsById(token))
            throw new TokenNotFoundException();

        TokenEntity tokenEntity = repository.getReferenceById(token);
        if (!tokenEntity.getTokenType().equals(tokenType))
            throw new TokenHasWrongTypeException();

        if (tokenEntity.isExpired())
            throw new TokenHasExpiredException();

        if (tokenEntity.getIsUsed())
            throw new TokenHasBeenUsedException();

        UserEntity user = tokenEntity.getUser();
        if (user == null)
            throw new TokenHasNoUserException();

        tokenEntity.setIsUsed(true);
        repository.saveAndFlush(tokenEntity);

        return user;
    }
}
