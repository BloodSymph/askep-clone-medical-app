package com.askep.auth.mapper;

import com.askep.auth.dto.auth.LoginRequest;
import com.askep.auth.dto.auth.RegisterRequest;
import com.askep.auth.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public static UserEntity mapRegisterRequestToUserEntity(RegisterRequest registerRequest) {
        return UserEntity.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .phoneNumber(registerRequest.getPhoneNumber())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .version(registerRequest.getVersion())
                .build();
    }

    public static UserEntity mapLoginRequestToUserEntity(LoginRequest loginRequest) {
        return UserEntity.builder()
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build();
    }

}
