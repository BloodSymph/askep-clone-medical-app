package com.askep.patient.repository;

import com.askep.patient.entity.InstrumentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentalRepository extends JpaRepository<InstrumentalEntity, Long> {
}
