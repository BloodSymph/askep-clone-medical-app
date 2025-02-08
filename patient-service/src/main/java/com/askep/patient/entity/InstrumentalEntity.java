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
@Entity(name = "instrumental")
@Table(name = "instumentals")
public class InstrumentalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pressure_instrumental", nullable = true)
    private String pressure;

    @Column(name = "electrocardiogram_instrumental", nullable = true)
    private String electrocardiogram;

    @Column(name = "electromyogram_instrumental", nullable = true)
    private String electromyogram;

    @Column(name = "encephalogram_instrumental", nullable = true)
    private String encephalogram;

    @Column(name = "roentgen_instrumental", nullable = true)
    private String roentgen;

    @Column(name = "tomography_instrumental", nullable = true)
    private String tomography;

    @CreationTimestamp
    @Column(name = "instrumentals_created")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "instrumentals_updatetAt")
    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "patients_email", referencedColumnName = "patients_email")
    private PatientEntity patient;

    @Version
    @Column(name = "instrumentals_version", nullable = false, unique = true)
    private Long version;

}
