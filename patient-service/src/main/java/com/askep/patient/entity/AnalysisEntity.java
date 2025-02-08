package com.askep.patient.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = "analyse")
@Table(name = "analysis")
public class AnalysisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blood_analysis", nullable = true)
    private String blood;

    @Column(name = "urine_analysis", nullable = true)
    private String urine;

    @Column(name = "sugar_analysis", nullable = true)
    private String sugar;

    @Column(name = "biochemistry_analusis", nullable = true)
    private String biochemistry;

    @Column(name = "immune_analysis", nullable = true)
    private String immune;

    @CreationTimestamp
    @Column(name = "analysis_created")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "analysis_updatetAt")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "patients_email", referencedColumnName = "patients_email")
    private PatientEntity patient;

    @Version
    @Column(name = "analysis_version", nullable = false, unique = true)
    private Long version;


}
