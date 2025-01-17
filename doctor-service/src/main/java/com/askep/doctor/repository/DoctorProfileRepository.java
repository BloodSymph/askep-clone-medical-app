package com.askep.doctor.repository;

import com.askep.doctor.entity.DoctorProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorProfileRepository extends JpaRepository<DoctorProfileEntity, Long> {

    Boolean existsByEmailIgnoreCase(String doctorEmail);

    Boolean existsByVersion(Long doctorProfileVersion);

    void deleteByEmailIgnoreCase(String doctorEmail);
    
}
