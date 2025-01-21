package com.askep.doctor.service.client.implementation;

import com.askep.doctor.dto.client.MedPersonalProfileClientRequest;
import com.askep.doctor.dto.client.MedPersonalProfileClientResponse;
import com.askep.doctor.repository.MedPersonalRepository;
import com.askep.doctor.service.client.MedPersonalProfileClientService;
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
