package com.askep.doctor.service.admin.implementation;

import com.askep.doctor.dto.admin.MedPersonalProfileAdminResponse;
import com.askep.doctor.dto.client.MedPersonalProfileClientRequest;
import com.askep.doctor.repository.MedPersonalRepository;
import com.askep.doctor.service.admin.MedPersonalProfileAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedPersonalProfileAdminServiceImpl implements MedPersonalProfileAdminService {

    private final MedPersonalRepository medPersonalRepository;

    @Override
    public Page<MedPersonalProfileAdminResponse> getAllDoctorProfiles(Pageable pageable) {
        return null;
    }

    @Override
    public Page<MedPersonalProfileAdminResponse> searchDoctorsBy(
            Pageable pageable, String searchText) {
        return null;
    }

    @Override
    public MedPersonalProfileAdminResponse getDoctorProfile(String doctorEmail) {
        return null;
    }

    @Override
    public MedPersonalProfileAdminResponse registerNewDoctorProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest) {
        return null;
    }

    @Override
    public MedPersonalProfileAdminResponse updateCurrentDoctorProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest) {
        return null;
    }

    @Override
    public void deleteDoctorProfile(
            String doctorEmail, Long doctorVersion) {

    }

}
