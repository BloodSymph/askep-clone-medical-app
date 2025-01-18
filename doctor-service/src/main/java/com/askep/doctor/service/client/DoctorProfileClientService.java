package com.askep.doctor.service.client;


import com.askep.doctor.dto.client.DoctorProfileClientRequest;
import com.askep.doctor.dto.client.DoctorProfileClientResponse;
import org.springframework.stereotype.Service;

@Service
public interface DoctorProfileClientService {

    DoctorProfileClientResponse getDoctorProfile();

    DoctorProfileClientResponse createDoctorProfile(
            DoctorProfileClientRequest doctorProfileClientRequest);
    DoctorProfileClientResponse updateDoctorProfile(
            DoctorProfileClientRequest doctorProfileClientRequest);

    void deleteDoctorProfile(String doctorEmail, Long doctorVersion);

}
