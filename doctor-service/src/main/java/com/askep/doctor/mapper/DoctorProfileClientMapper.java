package com.askep.doctor.mapper;

import com.askep.doctor.dto.client.DoctorProfileClientRequest;
import com.askep.doctor.dto.client.DoctorProfileClientResponse;
import com.askep.doctor.entity.DoctorProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class DoctorProfileClientMapper {

    public static DoctorProfileClientResponse mapToDoctorClientResponse(
            DoctorProfileEntity doctorProfileEntity) {
        return DoctorProfileClientResponse.builder()
                .id(doctorProfileEntity.getId())
                .firstName(doctorProfileEntity.getFirstName())
                .lastName(doctorProfileEntity.getLastName())
                .phoneNumber(doctorProfileEntity.getPhoneNumber())
                .email(doctorProfileEntity.getEmail())
                .address(doctorProfileEntity.getAddress())
                .specialization(doctorProfileEntity.getSpecialization())
                .build();
    }

    public static DoctorProfileEntity mapDoctorProfileRequestToEntity(
            DoctorProfileClientRequest doctorProfileClientRequest) {
        return DoctorProfileEntity.builder()
                .firstName(doctorProfileClientRequest.getFirstName())
                .lastName(doctorProfileClientRequest.getLastName())
                .phoneNumber(doctorProfileClientRequest.getPhoneNumber())
                .email(doctorProfileClientRequest.getEmail())
                .address(doctorProfileClientRequest.getAddress())
                .specialization(doctorProfileClientRequest.getSpecialization())
                .version(doctorProfileClientRequest.getVersion())
                .build();
    }

}
