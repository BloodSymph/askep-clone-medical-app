package com.askep.auth.service.admin.implementation;

import com.askep.auth.dto.admin.*;
import com.askep.auth.mapper.RoleAdminMapper;
import com.askep.auth.repository.RoleRepository;
import com.askep.auth.repository.UserRepository;
import com.askep.auth.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public Page<UserAdminResponse> getAllUsers(Pageable pageable) {
        return null;
    }

    @Override
    public Page<UserAdminResponse> findUsersBy(Pageable pageable, String searchText) {
        return null;
    }

    @Override
    public UserAdminDetailsResponse getUserInfo(String userEmail) {
        return null;
    }

    @Override
    @Transactional
    public UserAdminResponse registerNewUser(UserAdminRequest userAdminRequest) {
        return null;
    }

    @Override
    @Transactional
    public UserAdminResponse updateUser(UserAdminRequest userAdminRequest) {
        return null;
    }

    @Override
    @Transactional
    public void deleteUserBy(String userEmail, Long version) {

    }

    @Override
    public Page<RoleAdminMapper> getAllRoles(Pageable pageable) {
        return null;
    }

    @Override
    public RoleAdminResponse createNewRole(RoleAdminRequest roleAdminRequest) {
        return null;
    }

    @Override
    @Transactional
    public RoleAdminResponse updateRole(RoleAdminRequest roleAdminRequest) {
        return null;
    }

    @Override
    @Transactional
    public void deleteRoleBy(String roleName, Long version) {

    }

}
