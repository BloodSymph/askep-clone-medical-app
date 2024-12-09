package com.askep.auth.repository;

import com.askep.auth.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query("SELECT role FROM Role role " +
            "WHERE LOWER(role.name) LIKE CONCAT('%', :searchText, '%') "
    )
    Page<RoleEntity> searchRoleEntitiesBy(Pageable pageable, String searchText);

    Optional<RoleEntity> findByNameIgnoreCase(String roleName);

    Boolean existsByNameIgnoreCase(String roleName);

    Boolean existsByVersion(Long version);

    void deleteByNameIgnoreCase(String roleName);

}
