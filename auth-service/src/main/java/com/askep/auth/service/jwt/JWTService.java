package com.askep.auth.service.jwt;

import com.askep.auth.entity.UserEntity;
import com.askep.auth.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private Long accessTokenExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private Long refreshTokenExpiration;

    private final TokenRepository tokenRepository;

    public Boolean validateAccessToken(String accessToken, UserDetails userDetails) {
        String userEmail = extractUserEmail(accessToken);

        Boolean validToken = tokenRepository.findByAccessToken(accessToken)
                .map(tokenEntity -> !tokenEntity.getIsLoggedOut())
                .orElse(false);

        return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(accessToken) && validToken);
    }

    public Boolean validateRefreshToken(String refreshToken, UserDetails userDetails) {
        String userEmail = extractUserEmail(refreshToken);

        Boolean validToken = tokenRepository.findByRefreshToken(refreshToken)
                .map(tokenEntity -> !tokenEntity.getIsLoggedOut())
                .orElse(false);

        return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(refreshToken) && validToken);
    }

    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails, accessTokenExpiration);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(userDetails, refreshTokenExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String generateToken(UserDetails user, Long expireTime) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey())
                .claim("roles", user.getAuthorities())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
