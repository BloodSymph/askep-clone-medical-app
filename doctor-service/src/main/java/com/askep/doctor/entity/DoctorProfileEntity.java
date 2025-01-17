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

    @Column(name = "doctors_first_name", nullable = false)
    private String firstName;

    @Column(name = "doctors_last_name", nullable = false)
    private String lastName;

    @Column(name = "doctors_phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "doctors_email", nullable = false, unique = true)
    private String email;

    @Column(name = "doctors_address", nullable = false)
    private String address;

    @Column(name = "doctors_specialization", nullable = false)
    private String specialization;

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
