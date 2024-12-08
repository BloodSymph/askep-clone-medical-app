package com.askep.auth.mapper;

import com.askep.auth.dto.admin.UserAdminDetailsResponse;
import com.askep.auth.dto.admin.UserAdminRequest;
import com.askep.auth.dto.admin.UserAdminResponse;
import com.askep.auth.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserAdminMapper {

    public static UserAdminResponse mapToUserAdminResponse(UserEntity userEntity) {
        return UserAdminResponse.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .phoneNumber(userEntity.getPhoneNumber())
                .email(userEntity.getEmail())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }

    public static UserAdminDetailsResponse mapToUserAdminDetailedResponse(UserEntity userEntity) {
        return UserAdminDetailsResponse.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .phoneNumber(userEntity.getPhoneNumber())
                .email(userEntity.getEmail())
                .roles(userEntity.getRoles())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }

    public static UserEntity mapUserAdminRequestToEntity(UserAdminRequest userAdminRequest) {
        return UserEntity.builder()
                .firstName(userAdminRequest.getFirstName())
                .lastName(userAdminRequest.getLastName())
                .phoneNumber(userAdminRequest.getPhoneNumber())
                .email(userAdminRequest.getEmail())
                .password(userAdminRequest.getPassword())
                .version(userAdminRequest.getVersion())
                .build();
    }

}
