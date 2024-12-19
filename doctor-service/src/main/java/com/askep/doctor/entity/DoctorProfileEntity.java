package com.askep.doctor.entity;

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
@Entity(name = "DoctorProfile")
@Table(name = "doctors_profiles")
public class DoctorProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @CreationTimestamp
    @Column(name = "doctors_profile_created")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "doctors_profile_updatetAt")
    private LocalDateTime updatedAt;

    @Version
    @Column(name = "doctors_profile_version", nullable = false, unique = true)
    private Long version;

}
