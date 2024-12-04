package com.askep.auth.service.auth.implementation;

import com.askep.auth.dto.AuthenticationResponse;
import com.askep.auth.dto.LoginRequest;
import com.askep.auth.dto.RegisterRequest;
import com.askep.auth.entity.RoleEntity;
import com.askep.auth.entity.TokenEntity;
import com.askep.auth.entity.UserEntity;
import com.askep.auth.exception.exceptions.role.RoleNotFoundException;
import com.askep.auth.exception.exceptions.user.EmailIsTakenException;
import com.askep.auth.exception.exceptions.user.EmailNotFoundException;
import com.askep.auth.repository.RoleRepository;
import com.askep.auth.repository.TokenRepository;
import com.askep.auth.repository.UserRepository;
import com.askep.auth.service.auth.AuthService;
import com.askep.auth.service.jwt.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.io.IOException;
import java.util.List;

import static com.askep.auth.mapper.AuthMapper.mapRegisterRequestToUserEntity;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final static String BEARER_TOKEN = "Bearer ";

    private final static Integer TOKEN_BEGIN_INDEX = 7;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    @Override
    @Transactional
    public AuthenticationResponse registerNewUser(RegisterRequest registerRequest) {

        if (userRepository.existsByEmailIgnoreCase(registerRequest.getEmail())) {
            throw new EmailIsTakenException(
                    "Email: " + registerRequest.getEmail() + " is taken!"
            );
        }

        UserEntity userEntity = mapRegisterRequestToUserEntity(registerRequest);

        userEntity.setPassword(passwordEncoder.encode(
                registerRequest.getPassword()
        ));

        if (registerRequest.getIsAMedicalWorker()) {

            RoleEntity roleEntity = roleRepository
                    .findByNameIgnoreCase("DOCTOR")
                    .orElseThrow(
                            () -> new RoleNotFoundException(
                                    "Role not founded!"
                            )
                    );

            userEntity.setRoles(List.of(roleEntity));
            userRepository.save(userEntity);

        }

        RoleEntity roleEntity = roleRepository
                .findByNameIgnoreCase("PATIENT")
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Role not founded!"
                        )
                );

        userEntity.setRoles(List.of(roleEntity));
        userRepository.save(userEntity);

        String accessToken = jwtService.generateAccessToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);

        saveUserToken(accessToken, refreshToken, userEntity);

        return new AuthenticationResponse(
                accessToken, refreshToken,
                "User registration was successful!"
        );

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

        String authHeader = httpServletRequest.getHeader(AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith(BEARER_TOKEN)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(TOKEN_BEGIN_INDEX);

        String email = jwtService.extractUserEmail(token);

        UserEntity userEntity = userRepository
                .findByEmailIgnoreCase(email)
                .orElseThrow(
                        () -> new EmailNotFoundException(
                                "Can not find user by email: " + email + "!"
                        )
                );

        if (jwtService.validateRefreshToken(token, userEntity)) {

            String accessToken = jwtService.generateAccessToken(userEntity);
            String refreshToken = jwtService.generateRefreshToken(userEntity);

            revokeAllTokenByUser(userEntity);

            saveUserToken(accessToken, refreshToken, userEntity);

            return new ResponseEntity<>(
                    new AuthenticationResponse(
                            accessToken, refreshToken,
                            "New token is generated!"
                    ), HttpStatus.ACCEPTED);

        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private void revokeAllTokenByUser(UserEntity userEntity) {

        List<TokenEntity> validTokens = tokenRepository
                .findAllAccessTokensByUserId(userEntity.getId());

        if (validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(tokenEntity -> {
            tokenEntity.setIsLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);

    }

    private void saveUserToken(
            String accessToken, String refreshToken, UserEntity userEntity) {

        TokenEntity tokenEntity = new TokenEntity();

        tokenEntity.setAccessToken(accessToken);
        tokenEntity.setRefreshToken(refreshToken);
        tokenEntity.setIsLoggedOut(false);
        tokenEntity.setUser(userEntity);

        tokenRepository.save(tokenEntity);

    }

}
