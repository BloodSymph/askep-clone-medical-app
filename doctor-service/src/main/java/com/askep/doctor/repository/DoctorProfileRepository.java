package com.askep.doctor.repository;

import com.askep.doctor.entity.DoctorProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorProfileRepository extends JpaRepository<DoctorProfileEntity, Long> {

    @Query("SELECT doctor FROM DoctorProfile doctor " +
            "WHERE LOWER(doctor.firstName) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(doctor.lastName) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(doctor.phoneNumber) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(doctor.email) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(doctor.address) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(doctor.specialization) LIKE CONCAT('%', :searchText, '%') "
    )
    Page<DoctorProfileEntity> searchBy(
            @Param(value = "searchText") String searchText
    );

    Optional<DoctorProfileEntity> findByEmailIgnoreCase(String doctorEmail);

    Boolean existsByEmailIgnoreCase(String doctorEmail);

    Boolean existsByVersion(Long doctorProfileVersion);

    void deleteByEmailIgnoreCase(String doctorEmail);
    
}
