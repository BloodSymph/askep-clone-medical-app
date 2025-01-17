package com.askep.doctor.mapper;

import com.askep.doctor.dto.admin.DoctorProfileAdminResponse;
import com.askep.doctor.entity.DoctorProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class DoctorProfileAdminMapper {

    public static DoctorProfileAdminResponse mapToDoctorProfileAdminResponse(
            DoctorProfileEntity doctorProfileEntity) {
        return DoctorProfileAdminResponse.builder()
                .id(doctorProfileEntity.getId())
                .firstName(doctorProfileEntity.getFirstName())
                .lastName(doctorProfileEntity.getLastName())
                .phoneNumber(doctorProfileEntity.getPhoneNumber())
                .email(doctorProfileEntity.getEmail())
                .address(doctorProfileEntity.getAddress())
                .specialization(doctorProfileEntity.getSpecialization())
                .createdAt(doctorProfileEntity.getCreatedAt())
                .updatedAt(doctorProfileEntity.getUpdatedAt())
                .version(doctorProfileEntity.getVersion())
                .build();
    }

}
