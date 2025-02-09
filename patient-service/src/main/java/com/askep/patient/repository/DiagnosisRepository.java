package com.askep.patient.repository;

import com.askep.patient.entity.DiagnosisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends JpaRepository<DiagnosisEntity, Long> {

}
