package com.askep.auth.repository;

import com.askep.auth.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByNameIgnoreCase(String roleName);

    Boolean existsByNameIgnoreCase(String roleName);

    Boolean existsByVersion(Long version);

    void deleteByNameIgnoreCase(String roleName);

}
