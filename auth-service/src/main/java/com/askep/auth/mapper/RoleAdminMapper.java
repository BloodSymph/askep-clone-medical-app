package com.askep.auth.mapper;


import com.askep.auth.dto.admin.RoleAdminRequest;
import com.askep.auth.dto.admin.RoleAdminResponse;
import com.askep.auth.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleAdminMapper {

    public static RoleAdminResponse mapToRoleAdminResponse(RoleEntity roleEntity) {
        return RoleAdminResponse.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .createdAt(roleEntity.getCreatedAt())
                .updatedAt(roleEntity.getUpdatedAt())
                .build();
    }

    public static RoleEntity mapToRoleAdminRequestToEntity(RoleAdminRequest roleAdminRequest) {
        return RoleEntity.builder()
                .name(roleAdminRequest.getName())
                .version(roleAdminRequest.getVersion())
                .build();
    }

}
