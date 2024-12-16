package com.askep.auth.utils.security;

import com.askep.auth.entity.TokenEntity;
import com.askep.auth.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;

    private final static String BEARER_TOKEN = "Bearer ";

    private final static Integer TOKEN_BEGIN_INDEX = 7;

    @Override
    public void logout(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull Authentication authentication) {

        String authHeader = request.getHeader(AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith(BEARER_TOKEN)) {
            return;
        }

        String token = authHeader.substring(TOKEN_BEGIN_INDEX);

        TokenEntity storedToken = tokenRepository
                .findByAccessToken(token)
                .orElse(null);

        if(storedToken != null) {
            storedToken.setIsLoggedOut(true);
            tokenRepository.save(storedToken);
        }
    }

}
