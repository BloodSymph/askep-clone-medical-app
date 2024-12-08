package com.askep.auth.repository;

import com.askep.auth.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT user FROM User user WHERE LOWER(user.email) LIKE CONCAT('%', :searchText, '%') "
            + "OR LOWER(user.firstName) LIKE CONCAT('%', :searchText, '%')"
            + "OR LOWER(user.lastName) LIKE CONCAT('%', :searchText, '%')"
            + "OR LOWER(user.phoneNumber) LIKE CONCAT('%', :searchText, '%')"
    )
    Page<UserEntity> searchBy(Pageable pageable, @Param(value = "searchText") String searchText);

    @EntityGraph(value = "user-roles-named-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT user FROM User user WHERE LOWER(user.email) LIKE LOWER(:userEmail) ")
    Optional<UserEntity> getUserDetails(@Param(value = "userEmail") String userEmail);

    Optional<UserEntity> findByEmailIgnoreCase(String userEmail);

    Boolean existsByEmailIgnoreCase(String userEmail);

    Boolean existsByVersion(Long version);

    void deleteByEmailIgnoreCase(String email);

}
