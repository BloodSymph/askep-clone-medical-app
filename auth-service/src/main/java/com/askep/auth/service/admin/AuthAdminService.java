package com.askep.auth.service.admin;

import com.askep.auth.dto.admin.*;
import com.askep.auth.dto.admin.UserPermissionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface AuthAdminService {

    Page<UserAdminResponse> getAllUsers(Pageable pageable);

    Page<UserAdminResponse> findUsersBy(Pageable pageable, String searchText);

    UserAdminDetailsResponse getUserInfo(String userEmail);

    UserAdminResponse registerNewUser(UserAdminRequest userAdminRequest);

    UserAdminResponse updateUser(UserAdminRequest userAdminRequest);

    void givePermission(UserPermissionRequest userPermissionRequest);

    void deleteUserPermission(UserPermissionRequest userPermissionRequest);

    void deleteAllPermissions(String userEmail);

    void deleteUserBy(String userEmail, Long version);

    Page<RoleAdminResponse> getAllRoles(Pageable pageable);

    Page<RoleAdminResponse> searchRoles(Pageable pageable, String searchText);

    RoleAdminResponse createNewRole(RoleAdminRequest roleAdminRequest);

    RoleAdminResponse updateRole(RoleAdminRequest roleAdminRequest);

    void deleteRoleBy(String roleName, Long version);

    @Scheduled(fixedRate = 200)
    void evictAllCache();

}
