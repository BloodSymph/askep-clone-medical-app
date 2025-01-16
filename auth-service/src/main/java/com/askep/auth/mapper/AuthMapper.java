package com.askep.auth.mapper;

import com.askep.auth.dto.auth.LoginRequest;
import com.askep.auth.dto.auth.RegisterRequest;
import com.askep.auth.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public static UserEntity mapRegisterRequestToUserEntity(RegisterRequest registerRequest) {
        return UserEntity.builder()
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .version(registerRequest.getVersion())
                .build();
    }

}
