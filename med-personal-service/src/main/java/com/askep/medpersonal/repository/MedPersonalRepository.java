package com.askep.medpersonal.repository;

import com.askep.medpersonal.entity.MedPersonalProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedPersonalRepository extends JpaRepository<MedPersonalProfileEntity, Long> {

    @Query("SELECT doctor FROM DoctorProfile doctor " +
            "WHERE LOWER(doctor.firstName) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(doctor.lastName) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(doctor.phoneNumber) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(doctor.email) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(doctor.address) LIKE CONCAT('%', :searchText, '%') " +
            "OR LOWER(doctor.specialization) LIKE CONCAT('%', :searchText, '%') "
    )
    Page<MedPersonalProfileEntity> searchByText(
            Pageable pageable,
            @Param(value = "searchText") String searchText
    );

    Optional<MedPersonalProfileEntity> findByEmailIgnoreCase(String medPersonalProfileVersion);

    Boolean existsByEmailIgnoreCase(String medPersonalProfileVersion);

    Boolean existsByVersion(Long medPersonalProfileVersion);

    void deleteByEmailIgnoreCase(String medPersonalProfileVersion);
    
}
