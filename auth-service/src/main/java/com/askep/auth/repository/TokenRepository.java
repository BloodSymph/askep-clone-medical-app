package com.askep.auth.repository;

import com.askep.auth.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    @Query("SELECT tokens FROM Token tokens " +
            "INNER JOIN User user ON tokens.user.id = user.id" +
            " WHERE tokens.user.id = :userId AND tokens.isLoggedOut = false "
    )
    List<TokenEntity> findAllAccessTokensByUserId(
            @Param(value = "userId") Long userId
    );

    Optional<TokenEntity> findByAccessToken(String accessToken);

    Optional<TokenEntity> findByRefreshToken(String refreshToken);

}
