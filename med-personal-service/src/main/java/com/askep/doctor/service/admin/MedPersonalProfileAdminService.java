package com.askep.doctor.service.admin;


import com.askep.doctor.dto.admin.MedPersonalProfileAdminResponse;
import com.askep.doctor.dto.client.MedPersonalProfileClientRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface MedPersonalProfileAdminService {

    Page<MedPersonalProfileAdminResponse> getAllDoctorProfiles(Pageable pageable);

    Page<MedPersonalProfileAdminResponse> searchDoctorsBy(
            Pageable pageable, String searchText);

    MedPersonalProfileAdminResponse getDoctorProfile(String doctorEmail);

    MedPersonalProfileAdminResponse registerNewDoctorProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest);
    MedPersonalProfileAdminResponse updateCurrentDoctorProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest);

    void deleteDoctorProfile(String doctorEmail, Long doctorVersion);

}
