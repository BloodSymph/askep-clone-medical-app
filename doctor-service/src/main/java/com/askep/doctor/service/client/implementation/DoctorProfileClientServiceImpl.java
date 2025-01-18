package com.askep.doctor.service.client.implementation;

import com.askep.doctor.dto.client.DoctorProfileClientRequest;
import com.askep.doctor.dto.client.DoctorProfileClientResponse;
import com.askep.doctor.repository.DoctorProfileRepository;
import com.askep.doctor.service.client.DoctorProfileClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorProfileClientServiceImpl implements DoctorProfileClientService {

    private final DoctorProfileRepository doctorProfileRepository;

    @Override
    public DoctorProfileClientResponse getDoctorProfile() {
        return null;
    }

    @Override
    public DoctorProfileClientResponse createDoctorProfile(
            DoctorProfileClientRequest doctorProfileClientRequest) {
        return null;
    }

    @Override
    public DoctorProfileClientResponse updateDoctorProfile(
            DoctorProfileClientRequest doctorProfileClientRequest) {
        return null;
    }

    @Override
    public void deleteDoctorProfile(
            String doctorEmail, Long doctorVersion) {

    }

}
