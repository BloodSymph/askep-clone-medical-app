package com.askep.doctor.service.admin.implementation;

import com.askep.doctor.dto.admin.DoctorProfileAdminResponse;
import com.askep.doctor.dto.client.DoctorProfileClientRequest;
import com.askep.doctor.repository.DoctorProfileRepository;
import com.askep.doctor.service.admin.DoctorProfileAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorProfileAdminServiceImpl implements DoctorProfileAdminService {

    private final DoctorProfileRepository doctorProfileRepository;

    @Override
    public Page<DoctorProfileAdminResponse> getAllDoctorProfiles(Pageable pageable) {
        return null;
    }

    @Override
    public Page<DoctorProfileAdminResponse> searchDoctorsBy(
            Pageable pageable, String searchText) {
        return null;
    }

    @Override
    public DoctorProfileAdminResponse getDoctorProfile(String doctorEmail) {
        return null;
    }

    @Override
    public DoctorProfileAdminResponse registerNewDoctorProfile(
            DoctorProfileClientRequest doctorProfileClientRequest) {
        return null;
    }

    @Override
    public DoctorProfileAdminResponse updateCurrentDoctorProfile(
            DoctorProfileClientRequest doctorProfileClientRequest) {
        return null;
    }

    @Override
    public void deleteDoctorProfile(
            String doctorEmail, Long doctorVersion) {

    }

}
