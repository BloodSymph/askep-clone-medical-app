package com.askep.patient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "diagnosis")
@Table(name = "diagnoses")
public class DiagnosisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diagnoses_code")
    private String code;

    @Column(name = "diagnoses_name")
    private String name;

    @CreationTimestamp
    @Column(name = "diagnoses_created")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "diagnoses_updatetAt")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "patients_email", referencedColumnName = "patients_email")
    private PatientEntity patient;

    @Version
    @Column(name = "diagnoses_version", nullable = false, unique = true)
    private Long version;

}
