package com.askep.auth.controller;

import com.askep.auth.dto.admin.*;
import com.askep.auth.dto.admin.UserPermissionRequest;
import com.askep.auth.service.admin.AuthAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth-service/admin")
public class AuthAdminController {

    private final AuthAdminService authAdminService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserAdminResponse> getAllUsers(
            @PageableDefault(
                    sort = "email",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 15) Pageable pageable) {
        return authAdminService.getAllUsers(pageable);
    }

    @GetMapping("/users/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserAdminResponse> search(
            @PageableDefault(
                    sort = "email",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 15) Pageable pageable,
            @RequestParam(
                    value = "searchText",
                    required = false,
                    defaultValue = "") String searchText) {
        return authAdminService.findUsersBy(pageable, searchText);
    }

    @GetMapping("/users/{userEmail}/get-info")
    @ResponseStatus(HttpStatus.OK)
    public UserAdminDetailsResponse getUserDetails(
            @PathVariable(value = "userEmail") String userEmail) {
        return authAdminService.getUserInfo(userEmail);
    }

    @PostMapping("/users/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserAdminResponse creteNewUser(
            @Valid @RequestBody UserAdminRequest userAdminRequest) {
        return authAdminService.registerNewUser(userAdminRequest);
    }

    @PutMapping("/users/update")
    @ResponseStatus(HttpStatus.CREATED)
    public UserAdminResponse updateUser(
            @Valid @RequestBody UserAdminRequest userAdminRequest) {
        return authAdminService.updateUser(userAdminRequest);
    }

    @PostMapping("/users/give-permission")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> givePermission(
            @Valid @RequestBody UserPermissionRequest userPermissionRequest) {
        authAdminService.givePermission(userPermissionRequest);
        return new ResponseEntity<>("Permission give successful!", HttpStatus.OK);
    }

    @DeleteMapping("/users/remove-permission")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removePermissionFromUser(
            @Valid @RequestBody UserPermissionRequest userPermissionRequest) {
        authAdminService.deleteUserPermission(userPermissionRequest);
        return new ResponseEntity<>("Permission remove successful!", HttpStatus.OK);
    }

    @DeleteMapping("/users/{userEmail}/remove-all-permissions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removeAllPermissions(
            @PathVariable(value = "userEmail") String userEmail) {
        authAdminService.deleteAllPermissions(userEmail);
        return new ResponseEntity<>("Permissions removed successful!", HttpStatus.OK);
    }

    @DeleteMapping("/users/{userEmail}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteUserBy(
            @PathVariable(value = "userEmail") String userEmail,
            @RequestParam(value = "userVersion") Long userVersion) {
        authAdminService.deleteUserBy(userEmail, userVersion);
        return new ResponseEntity<>("User deleted successful!", HttpStatus.OK);
    }

    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    public Page<RoleAdminResponse> getAllRoles(
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 15) Pageable pageable ){
        return authAdminService.getAllRoles(pageable);
    }

    @GetMapping("/roles/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<RoleAdminResponse> searchRoles(
            @PageableDefault(
                    sort = "name",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 15) Pageable pageable,
            @RequestParam(
                    value = "searchText",
                    required = false,
                    defaultValue = "") String searchText) {
        return authAdminService.searchRoles(pageable, searchText);
    }

    @PostMapping("/roles/create")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleAdminResponse createNewRole(
            @Valid @RequestBody RoleAdminRequest roleAdminRequest) {
        return authAdminService.createNewRole(roleAdminRequest);
    }

    @PutMapping("/roles/update")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleAdminResponse updateCurrentRole(
            @Valid @RequestBody RoleAdminRequest roleAdminRequest) {
        return authAdminService.updateRole(roleAdminRequest);
    }

    @DeleteMapping("/roles/{roleName}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteCurrentRole(
            @PathVariable(value = "roleName") String userEmail,
            @RequestParam(value = "roleVersion") Long userVersion) {
        authAdminService.deleteRoleBy(userEmail, userVersion);
        return new ResponseEntity<>("Role successful deleted!", HttpStatus.OK);
    }

}
