package com.askep.medpersonal.utils.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;


@Component
public class JWTUtil {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private Long accessTokenExpiration;

    public Boolean validateToken(String accessToken) {
        return !isTokenExpired(accessToken);
    }

    public String extractUserEmail(String accessToken) {
        return extractClaim(accessToken, Claims::getSubject);
    }

    private Boolean isTokenExpired(String accessToken) {
        return extractExpiration(accessToken).before(new Date());
    }

    private Date extractExpiration(String accessToken) {
        return extractClaim(accessToken, Claims::getExpiration);
    }

    public <T> T extractClaim(String accessToken, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(accessToken);
        return resolver.apply(claims);
    }

    public Claims extractAllClaims(String accessToken) {

        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(accessToken)
                .getPayload();

    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
