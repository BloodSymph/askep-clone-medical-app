package com.askep.doctor.mapper;

import com.askep.doctor.dto.admin.MedPersonalProfileAdminResponse;
import com.askep.doctor.entity.MedPersonalProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class MedPersonalAdminMapper {

    public static MedPersonalProfileAdminResponse mapToDoctorProfileAdminResponse(
            MedPersonalProfileEntity medPersonalProfileEntity) {
        return MedPersonalProfileAdminResponse.builder()
                .id(medPersonalProfileEntity.getId())
                .firstName(medPersonalProfileEntity.getFirstName())
                .lastName(medPersonalProfileEntity.getLastName())
                .phoneNumber(medPersonalProfileEntity.getPhoneNumber())
                .email(medPersonalProfileEntity.getEmail())
                .address(medPersonalProfileEntity.getAddress())
                .specialization(medPersonalProfileEntity.getSpecialization())
                .createdAt(medPersonalProfileEntity.getCreatedAt())
                .updatedAt(medPersonalProfileEntity.getUpdatedAt())
                .version(medPersonalProfileEntity.getVersion())
                .build();
    }

}
