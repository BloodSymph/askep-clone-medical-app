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
@Entity(name = "PatientEntity")
@Table(name = "patients")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patients_first_name", nullable = false)
    private String firstName;

    @Column(name = "patientsl_last_name", nullable = false)
    private String lastName;

    @Column(name = "patients_phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "patients_email", nullable = false, unique = true)
    private String email;

    @Column(name = "patients_address", nullable = false)
    private String address;

    @Column(name = "patients_work", nullable = false)
    private String work;

    @CreationTimestamp
    @Column(name = "patients_profile_created")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "patients_profile_updatetAt")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "patient")
    private AnalysisEntity analysis;

    @OneToOne(mappedBy = "patient")
    private InstrumentalEntity instrumentalEntity;

    @OneToOne(mappedBy = "patient")
    private DiagnosisEntity diagnosis;

    @Embedded
    @Column(name = "patients_status")
    private PatientStatus patientStatus;

    @Version
    @Column(name = "patients_profile_version", nullable = false, unique = true)
    private Long version;

}
