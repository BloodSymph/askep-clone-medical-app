package com.askep.doctor.mapper;

import com.askep.doctor.dto.client.MedPersonalProfileClientRequest;
import com.askep.doctor.dto.client.MedPersonalProfileClientResponse;
import com.askep.doctor.entity.MedPersonalProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class MedPersonalClientMapper {

    public static MedPersonalProfileClientResponse mapToDoctorClientResponse(
            MedPersonalProfileEntity medPersonalProfileEntity) {
        return MedPersonalProfileClientResponse.builder()
                .id(medPersonalProfileEntity.getId())
                .firstName(medPersonalProfileEntity.getFirstName())
                .lastName(medPersonalProfileEntity.getLastName())
                .phoneNumber(medPersonalProfileEntity.getPhoneNumber())
                .email(medPersonalProfileEntity.getEmail())
                .address(medPersonalProfileEntity.getAddress())
                .specialization(medPersonalProfileEntity.getSpecialization())
                .build();
    }

    public static MedPersonalProfileEntity mapDoctorProfileRequestToEntity(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest) {
        return MedPersonalProfileEntity.builder()
                .firstName(medPersonalProfileClientRequest.getFirstName())
                .lastName(medPersonalProfileClientRequest.getLastName())
                .phoneNumber(medPersonalProfileClientRequest.getPhoneNumber())
                .email(medPersonalProfileClientRequest.getEmail())
                .address(medPersonalProfileClientRequest.getAddress())
                .specialization(medPersonalProfileClientRequest.getSpecialization())
                .version(medPersonalProfileClientRequest.getVersion())
                .build();
    }

}
