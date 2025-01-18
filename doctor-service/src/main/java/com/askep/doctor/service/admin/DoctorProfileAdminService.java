package com.askep.doctor.service.admin;


import com.askep.doctor.dto.admin.DoctorProfileAdminResponse;
import com.askep.doctor.dto.client.DoctorProfileClientRequest;
import com.askep.doctor.dto.client.DoctorProfileClientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface DoctorProfileAdminService {

    Page<DoctorProfileAdminResponse> getAllDoctorProfiles(Pageable pageable);

    Page<DoctorProfileAdminResponse> searchDoctorsBy(
            Pageable pageable, String searchText);

    DoctorProfileAdminResponse getDoctorProfile(String doctorEmail);

    DoctorProfileAdminResponse registerNewDoctorProfile(
            DoctorProfileClientRequest doctorProfileClientRequest);
    DoctorProfileAdminResponse updateCurrentDoctorProfile(
            DoctorProfileClientRequest doctorProfileClientRequest);

    void deleteDoctorProfile(String doctorEmail, Long doctorVersion);

}
