package com.askep.auth.controller;

import com.askep.auth.dto.auth.AuthenticationResponse;
import com.askep.auth.dto.auth.ChangePasswordRequest;
import com.askep.auth.dto.auth.LoginRequest;
import com.askep.auth.dto.auth.RegisterRequest;
import com.askep.auth.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth-service/client")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponse registerLike(
            @Valid @RequestBody RegisterRequest registerRequest) {
        return authService.registerNewUser(registerRequest);
    }

    @PostMapping("/change-password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> changePassword(
            @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return authService.changPassword(changePasswordRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthenticationResponse loginLike (
            @Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> refreshRequest(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws IOException {
        return authService.refresh(httpServletRequest, httpServletResponse);
    }

}
