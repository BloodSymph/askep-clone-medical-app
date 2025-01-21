package com.askep.medpersonal.service.client;


import com.askep.medpersonal.dto.client.MedPersonalProfileClientRequest;
import com.askep.medpersonal.dto.client.MedPersonalProfileClientResponse;
import org.springframework.stereotype.Service;

@Service
public interface MedPersonalProfileClientService {

    MedPersonalProfileClientResponse getDoctorProfile();

    MedPersonalProfileClientResponse createDoctorProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest);
    MedPersonalProfileClientResponse updateDoctorProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest);

    void deleteDoctorProfile(Long medPersonalProfileVersion);

}
