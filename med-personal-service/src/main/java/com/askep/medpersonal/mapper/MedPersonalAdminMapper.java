package com.askep.medpersonal.mapper;

import com.askep.medpersonal.dto.admin.MedPersonaProfileAdminRequest;
import com.askep.medpersonal.dto.admin.MedPersonalProfileAdminResponse;
import com.askep.medpersonal.entity.MedPersonalProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class MedPersonalAdminMapper {

    public static MedPersonalProfileAdminResponse mapToProfileAdminResponse(
            MedPersonalProfileEntity medPersonalProfileEntity) {
        return MedPersonalProfileAdminResponse.builder()
                .id(medPersonalProfileEntity.getId())
                .firstName(medPersonalProfileEntity.getFirstName())
                .lastName(medPersonalProfileEntity.getLastName())
                .phoneNumber(medPersonalProfileEntity.getPhoneNumber())
                .email(medPersonalProfileEntity.getEmail())
                .address(medPersonalProfileEntity.getAddress())
                .specialization(medPersonalProfileEntity.getSpecialization())
                .photoPath(medPersonalProfileEntity.getPhotoUrl())
                .createdAt(medPersonalProfileEntity.getCreatedAt())
                .updatedAt(medPersonalProfileEntity.getUpdatedAt())
                .version(medPersonalProfileEntity.getVersion())
                .build();
    }

    public static MedPersonalProfileEntity mapProfileAdminRequestToEntity(
            MedPersonaProfileAdminRequest medPersonaProfileAdminRequest) {
        return MedPersonalProfileEntity.builder()
                .firstName(medPersonaProfileAdminRequest.getFirstName())
                .lastName(medPersonaProfileAdminRequest.getLastName())
                .phoneNumber(medPersonaProfileAdminRequest.getPhoneNumber())
                .email(medPersonaProfileAdminRequest.getEmail())
                .address(medPersonaProfileAdminRequest.getAddress())
                .specialization(medPersonaProfileAdminRequest.getSpecialization())
                .version(medPersonaProfileAdminRequest.getVersion())
                .build();
    }
}
