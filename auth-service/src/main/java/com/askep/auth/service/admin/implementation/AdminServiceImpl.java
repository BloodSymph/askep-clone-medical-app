package com.askep.auth.service.admin.implementation;

import com.askep.auth.dto.admin.*;
import com.askep.auth.dto.auth.UserPermissionRequest;
import com.askep.auth.entity.RoleEntity;
import com.askep.auth.entity.UserEntity;
import com.askep.auth.exception.exceptions.role.RoleEntityVersionNotValidException;
import com.askep.auth.exception.exceptions.role.RoleNotFoundException;
import com.askep.auth.exception.exceptions.user.UserEntityVersionNotValidException;
import com.askep.auth.exception.exceptions.user.UserNotFoundException;
import com.askep.auth.mapper.RoleAdminMapper;
import com.askep.auth.mapper.UserAdminMapper;
import com.askep.auth.repository.RoleRepository;
import com.askep.auth.repository.UserRepository;
import com.askep.auth.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.askep.auth.mapper.RoleAdminMapper.mapToRoleAdminRequestToEntity;
import static com.askep.auth.mapper.RoleAdminMapper.mapToRoleAdminResponse;
import static com.askep.auth.mapper.UserAdminMapper.*;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public Page<UserAdminResponse> getAllUsers(Pageable pageable) {
        return userRepository
                .findAll(pageable)
                .map(UserAdminMapper::mapToUserAdminResponse);
    }

    @Override
    public Page<UserAdminResponse> findUsersBy(Pageable pageable, String searchText) {
        return userRepository
                .searchBy(pageable, searchText)
                .map(UserAdminMapper::mapToUserAdminResponse);
    }

    @Override
    public UserAdminDetailsResponse getUserInfo(String userEmail) {
        UserEntity userEntity = userRepository
                .getUserDetails(userEmail)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by email: " + userEmail + "!"
                        )
                );
        return mapToUserAdminDetailedResponse(userEntity);
    }

    @Override
    public UserAdminResponse registerNewUser(UserAdminRequest userAdminRequest) {
        UserEntity userEntity = mapUserAdminRequestToEntity(userAdminRequest);
        userRepository.save(userEntity);
        return mapToUserAdminResponse(userEntity);
    }

    @Override
    @Transactional
    public UserAdminResponse updateUser(UserAdminRequest userAdminRequest) {

        if (!userRepository.existsByVersion(userAdminRequest.getVersion())) {
            throw new UserEntityVersionNotValidException(
                    "User Entity version not valid!"
            );
        }

        UserEntity userEntity = userRepository
                .findByEmailIgnoreCase(userAdminRequest.getEmail())
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by email: " + userAdminRequest.getEmail() + "!"
                        )
                );

        userEntity.setFirstName(userAdminRequest.getFirstName());
        userEntity.setLastName(userAdminRequest.getLastName());
        userEntity.setPhoneNumber(userAdminRequest.getPhoneNumber());
        userEntity.setEmail(userAdminRequest.getEmail());
        userEntity.setPassword(
                passwordEncoder.encode(userAdminRequest.getPassword())
        );
        userEntity.setVersion(userAdminRequest.getVersion());
        userRepository.save(userEntity);
        return mapToUserAdminResponse(userEntity);
    }

    @Override
    @Transactional
    public void givePermission(UserPermissionRequest userPermissionRequest) {

        UserEntity userEntity = userRepository
                .findByEmailIgnoreCase(userPermissionRequest.getEmail())
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by email: " + userPermissionRequest.getEmail() + "!"
                        )
                );

        RoleEntity roleEntity = roleRepository
                .findByNameIgnoreCase(userPermissionRequest.getRoleName())
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Can not find role by name: " + userPermissionRequest.getRoleName() + " !"
                        )
                );

        userEntity.getRoles().add(roleEntity);

        userRepository.save(userEntity);

    }

    @Override
    @Transactional
    public void deleteUserPermission(UserPermissionRequest userPermissionRequest) {

        UserEntity userEntity = userRepository
                .findByEmailIgnoreCase(userPermissionRequest.getEmail())
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by email: " + userPermissionRequest.getEmail() + "!"
                        )
                );

        RoleEntity roleEntity = roleRepository
                .findByNameIgnoreCase(userPermissionRequest.getRoleName())
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Can not find role by name: " + userPermissionRequest.getRoleName() + " !"
                        )
                );

        userEntity.getRoles().remove(roleEntity);

        userRepository.save(userEntity);

    }

    @Override
    @Transactional
    public void deleteAllPermissions(String userEmail) {

        UserEntity userEntity = userRepository
                .findByEmailIgnoreCase(userEmail)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                "Can not find user by email: " + userEmail + "!"
                        )
                );

        userEntity.getRoles().clear();

        userRepository.save(userEntity);

    }

    @Override
    @Transactional
    public void deleteUserBy(String userEmail, Long version) {
        if (!userRepository.existsByEmailIgnoreCase(userEmail)) {
            throw new UserNotFoundException(
                    "Can not find user by email: " + userEmail + "!"
            );
        }
        if (!userRepository.existsByVersion(version)) {
            throw new UserEntityVersionNotValidException(
                    "User Entity version not valid!"
            );
        }
        userRepository.deleteByEmailIgnoreCase(userEmail);
    }

    @Override
    public Page<RoleAdminResponse> getAllRoles(Pageable pageable) {
        return roleRepository
                .findAll(pageable)
                .map(RoleAdminMapper::mapToRoleAdminResponse);
    }

    @Override
    public RoleAdminResponse createNewRole(RoleAdminRequest roleAdminRequest) {
        RoleEntity roleEntity = mapToRoleAdminRequestToEntity(roleAdminRequest);
        roleRepository.save(roleEntity);
        return mapToRoleAdminResponse(roleEntity);
    }

    @Override
    @Transactional
    public RoleAdminResponse updateRole(RoleAdminRequest roleAdminRequest) {

        if (!roleRepository.existsByVersion(roleAdminRequest.getVersion())) {
            throw new RoleEntityVersionNotValidException(
                    "Role Entity version not valid!"
            );
        }

        RoleEntity roleEntity = roleRepository
                .findByNameIgnoreCase(roleAdminRequest.getName())
                .orElseThrow(
                        () -> new RoleNotFoundException(
                                "Can not find role by name: " + roleAdminRequest.getName() + " !"
                        )
                );

        roleEntity.setName(roleAdminRequest.getName());
        roleEntity.setVersion(roleEntity.getVersion());

        roleRepository.save(roleEntity);

        return mapToRoleAdminResponse(roleEntity);
    }

    @Override
    @Transactional
    public void deleteRoleBy(String roleName, Long version) {
        if (!roleRepository.existsByVersion(version)) {
            throw new RoleEntityVersionNotValidException(
                    "Role Entity version not valid!"
            );
        }
        if (!roleRepository.existsByNameIgnoreCase(roleName)) {
            throw new RoleNotFoundException(
                    "Can not find role by name: " + roleName + " !"
            );
        }
        roleRepository.deleteByNameIgnoreCase(roleName);
    }

}
