package com.askep.auth.service.auth.implementation;

import com.askep.auth.dto.AuthenticationResponse;
import com.askep.auth.dto.ChangePasswordRequest;
import com.askep.auth.dto.LoginRequest;
import com.askep.auth.dto.RegisterRequest;
import com.askep.auth.entity.RoleEntity;
import com.askep.auth.entity.TokenEntity;
import com.askep.auth.entity.UserEntity;
import com.askep.auth.exception.exceptions.role.RoleNotFoundException;
import com.askep.auth.exception.exceptions.user.EmailIsTakenException;
import com.askep.auth.exception.exceptions.user.IncorrectPasswordException;
import com.askep.auth.exception.exceptions.user.UserNotFoundException;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

import static com.askep.auth.mapper.AuthMapper.mapRegisterRequestToUserEntity;
import static com.askep.auth.utils.security.AuthoritiesConverter.getAuthorities;
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

        RoleEntity roleEntity = roleRepository
                .findByNameIgnoreCase(registerRequest.getRoleName())
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Role not founded!"
                        )
                );

        userEntity.setRoles(List.of(roleEntity));
        userEntity = userRepository.save(userEntity);

        String accessToken = jwtService.generateAccessToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);

        saveUserToken(accessToken, refreshToken, userEntity);

        return new AuthenticationResponse(
                accessToken, refreshToken,
                "User authentication was successful!"
        );

    }

    @Override
    @Transactional
    public ResponseEntity<?> changPassword(ChangePasswordRequest changePasswordRequest) {

        UserEntity userEntity = userRepository
                .findByEmailIgnoreCase(changePasswordRequest.getEmail())
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user by username: " + changePasswordRequest.getEmail() + "!"
                        )
                );

        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), userEntity.getPassword())) {
            throw new IncorrectPasswordException(
                    "Current password: " + changePasswordRequest.getCurrentPassword() + "is incorrect!"
            );
        }

        userEntity.setPassword(passwordEncoder.encode(
                changePasswordRequest.getNewPassword()
        ));

        userRepository.save(userEntity);

        return new ResponseEntity<>(
                "Password change was successful!", HttpStatus.ACCEPTED
        );
    }

    @Override
    @Transactional
    public AuthenticationResponse login(LoginRequest loginRequest) {

        RoleEntity roleEntity = roleRepository.findByNameIgnoreCase(loginRequest.getRoleName()).orElseThrow();

        Collection<RoleEntity> roleEntities = List.of(roleEntity);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword(),
                        getAuthorities(roleEntities)
                )
        );

        UserEntity userEntity = userRepository
                .findByEmailIgnoreCase(loginRequest.getEmail())
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find user by username: " + loginRequest.getEmail() + "!"
                        )
                );

        String accessToken = jwtService.generateAccessToken(userEntity);
        String refreshToken = jwtService.generateRefreshToken(userEntity);

        revokeAllTokenByUser(userEntity);
        saveUserToken(accessToken, refreshToken, userEntity);

        return new AuthenticationResponse(
                accessToken, refreshToken,
                "User authorization was successful!"
        );

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
                        () -> new UserNotFoundException(
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
