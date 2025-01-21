package com.askep.doctor.service.client;


import com.askep.doctor.dto.client.MedPersonalProfileClientRequest;
import com.askep.doctor.dto.client.MedPersonalProfileClientResponse;
import org.springframework.stereotype.Service;

@Service
public interface MedPersonalProfileClientService {

    MedPersonalProfileClientResponse getDoctorProfile();

    MedPersonalProfileClientResponse createDoctorProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest);
    MedPersonalProfileClientResponse updateDoctorProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest);

    void deleteDoctorProfile(String doctorEmail, Long doctorVersion);

}
