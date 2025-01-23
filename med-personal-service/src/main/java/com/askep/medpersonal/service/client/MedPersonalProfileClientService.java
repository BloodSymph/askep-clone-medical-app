package com.askep.medpersonal.service.client;


import com.askep.medpersonal.dto.client.MedPersonalProfileClientRequest;
import com.askep.medpersonal.dto.client.MedPersonalProfileClientResponse;
import org.springframework.stereotype.Service;

@Service
public interface MedPersonalProfileClientService {

    MedPersonalProfileClientResponse getMedPersonalProfile();

    MedPersonalProfileClientResponse createProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest);
    MedPersonalProfileClientResponse updateProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest);

    void deleteProfile(Long medPersonalProfileVersion);

}
