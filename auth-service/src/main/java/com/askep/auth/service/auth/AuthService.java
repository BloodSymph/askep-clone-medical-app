package com.askep.auth.service.auth;

import com.askep.auth.dto.auth.AuthenticationResponse;
import com.askep.auth.dto.auth.ChangePasswordRequest;
import com.askep.auth.dto.auth.LoginRequest;
import com.askep.auth.dto.auth.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface AuthService {

    AuthenticationResponse registerNewUser(RegisterRequest registerRequest);

    AuthenticationResponse login(LoginRequest loginRequest);

    ResponseEntity<?> changPassword(ChangePasswordRequest changePasswordRequest);

    ResponseEntity<?> refresh(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws IOException;


}
