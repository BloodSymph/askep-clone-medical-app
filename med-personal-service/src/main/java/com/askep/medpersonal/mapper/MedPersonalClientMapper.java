package com.askep.medpersonal.mapper;

import com.askep.medpersonal.dto.client.MedPersonalProfileClientRequest;
import com.askep.medpersonal.dto.client.MedPersonalProfileClientResponse;
import com.askep.medpersonal.entity.MedPersonalProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class MedPersonalClientMapper {

    public static MedPersonalProfileClientResponse mapToMedPersonalClientResponse(
            MedPersonalProfileEntity medPersonalProfileEntity) {
        return MedPersonalProfileClientResponse.builder()
                .id(medPersonalProfileEntity.getId())
                .firstName(medPersonalProfileEntity.getFirstName())
                .lastName(medPersonalProfileEntity.getLastName())
                .phoneNumber(medPersonalProfileEntity.getPhoneNumber())
                .email(medPersonalProfileEntity.getEmail())
                .address(medPersonalProfileEntity.getAddress())
                .specialization(medPersonalProfileEntity.getSpecialization())
                .photoPath(medPersonalProfileEntity.getPhotoUrl())
                .build();
    }

    public static MedPersonalProfileEntity mapMedPersonalProfileRequestToEntity(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest) {
        return MedPersonalProfileEntity.builder()
                .firstName(medPersonalProfileClientRequest.getFirstName())
                .lastName(medPersonalProfileClientRequest.getLastName())
                .phoneNumber(medPersonalProfileClientRequest.getPhoneNumber())
                .address(medPersonalProfileClientRequest.getAddress())
                .specialization(medPersonalProfileClientRequest.getSpecialization())
                .version(medPersonalProfileClientRequest.getVersion())
                .build();
    }

}
