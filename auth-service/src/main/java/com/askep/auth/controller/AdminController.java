package com.askep.auth.controller;

import com.askep.auth.dto.admin.UserAdminDetailsResponse;
import com.askep.auth.dto.admin.UserAdminRequest;
import com.askep.auth.dto.admin.UserAdminResponse;
import com.askep.auth.dto.auth.UserPermissionRequest;
import com.askep.auth.service.admin.AdminService;
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
public class AdminController {

    private final AdminService adminService;

    //todo: role search
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserAdminResponse> getAllUsers(
            @PageableDefault(
                    sort = "email",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 15) Pageable pageable) {
        return adminService.getAllUsers(pageable);
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
        return adminService.findUsersBy(pageable, searchText);
    }

    @GetMapping("/users/{userEmail}/get-info")
    @ResponseStatus(HttpStatus.OK)
    public UserAdminDetailsResponse getUserDetails(
            @PathVariable(value = "userEmail") String userEmail) {
        return adminService.getUserInfo(userEmail);
    }

    @PostMapping("/users/create")
    @ResponseStatus(HttpStatus.OK)
    public UserAdminResponse creteNewUser(
            @Valid @RequestBody UserAdminRequest userAdminRequest) {
        return adminService.registerNewUser(userAdminRequest);
    }

    @PutMapping("/users/update")
    @ResponseStatus(HttpStatus.OK)
    public UserAdminResponse updateUser(
            @Valid @RequestBody UserAdminRequest userAdminRequest) {
        return adminService.updateUser(userAdminRequest);
    }

    @PostMapping("/users/give-permission")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> givePermission(
            @Valid @RequestBody UserPermissionRequest userPermissionRequest) {
        adminService.givePermission(userPermissionRequest);
        return new ResponseEntity<>("Permission give successful!", HttpStatus.OK);
    }

    @DeleteMapping("/users/remove-permission")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> removePermissionFromUser(
            @Valid @RequestBody UserPermissionRequest userPermissionRequest) {
        adminService.deleteUserPermission(userPermissionRequest);
        return new ResponseEntity<>("Permission remove successful!", HttpStatus.OK);
    }




}
