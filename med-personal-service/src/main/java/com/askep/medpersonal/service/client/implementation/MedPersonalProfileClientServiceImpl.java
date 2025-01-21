package com.askep.medpersonal.service.client.implementation;

import com.askep.medpersonal.dto.client.MedPersonalProfileClientRequest;
import com.askep.medpersonal.dto.client.MedPersonalProfileClientResponse;
import com.askep.medpersonal.repository.MedPersonalRepository;
import com.askep.medpersonal.service.client.MedPersonalProfileClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedPersonalProfileClientServiceImpl implements MedPersonalProfileClientService {

    private final MedPersonalRepository medPersonalRepository;

    @Override
    public MedPersonalProfileClientResponse getDoctorProfile() {
        return null;
    }

    @Override
    public MedPersonalProfileClientResponse createDoctorProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest) {
        return null;
    }

    @Override
    public MedPersonalProfileClientResponse updateDoctorProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest) {
        return null;
    }

    @Override
    public void deleteDoctorProfile(
            String doctorEmail, Long doctorVersion) {

    }

}
