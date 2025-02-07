package com.askep.medpersonal.entity;

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
public class MedPersonalProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "med_personal_first_name", nullable = false)
    private String firstName;

    @Column(name = "med_personal_last_name", nullable = false)
    private String lastName;

    @Column(name = "med_personal_phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "med_personal_email", nullable = false, unique = true)
    private String email;

    @Column(name = "med_personal_address", nullable = false)
    private String address;

    @Column(name = "med_personal_specialization", nullable = false)
    private String specialization;

    @Column(name = "med_personal_photo", nullable = true)
    private String photoUrl;

    @CreationTimestamp
    @Column(name = "med_personal_profile_created")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "med_personal_profile_updatetAt")
    private LocalDateTime updatedAt;

    @Version
    @Column(name = "med_personal_profile_version", nullable = false, unique = true)
    private Long version;

}
