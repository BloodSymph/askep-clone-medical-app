package com.askep.auth.service.auth.implementation;

import com.askep.auth.dto.AuthenticationResponse;
import com.askep.auth.dto.LoginRequest;
import com.askep.auth.dto.RegisterRequest;
import com.askep.auth.entity.TokenEntity;
import com.askep.auth.entity.UserEntity;
import com.askep.auth.repository.RoleRepository;
import com.askep.auth.repository.TokenRepository;
import com.askep.auth.repository.UserRepository;
import com.askep.auth.service.auth.AuthService;
import com.askep.auth.service.jwt.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    @Override
    @Transactional
    public AuthenticationResponse registerNewUser(RegisterRequest registerRequest) {
        return null;
    }

    @Override
    @Transactional
    public AuthenticationResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<?> refresh(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws IOException {
        return null;
    }

    private void revokeAllTokenByUser(UserEntity user) {

        List<TokenEntity> validTokens = tokenRepository
                .findAllAccessTokensByUserId(user.getId());

        if (validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(tokenEntity -> {
            tokenEntity.setIsLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);

    }

    private void saveUserToken(
            String accessToken, String refreshToken, UserEntity user) {

        TokenEntity tokenEntity = new TokenEntity();

        tokenEntity.setAccessToken(accessToken);
        tokenEntity.setRefreshToken(refreshToken);
        tokenEntity.setIsLoggedOut(false);
        tokenEntity.setUser(user);

        tokenRepository.save(tokenEntity);

    }

}
