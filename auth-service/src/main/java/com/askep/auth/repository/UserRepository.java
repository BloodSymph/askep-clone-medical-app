package com.askep.auth.repository;

import com.askep.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmailIgnoreCase(String userEmail);

    Boolean existsByEmailIgnoreCase(String userEmail);

    Boolean existsByVersion(Long version);

    void deleteByEmailIgnoreCase(String email);

}
