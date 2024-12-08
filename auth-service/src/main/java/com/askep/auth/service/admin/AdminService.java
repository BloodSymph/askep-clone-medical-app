package com.askep.auth.service.admin;

import com.askep.auth.dto.admin.*;
import com.askep.auth.dto.auth.UserPermissionRequest;
import com.askep.auth.mapper.RoleAdminMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

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

    RoleAdminResponse createNewRole(RoleAdminRequest roleAdminRequest);

    RoleAdminResponse updateRole(RoleAdminRequest roleAdminRequest);

    void deleteRoleBy(String roleName, Long version);


}
