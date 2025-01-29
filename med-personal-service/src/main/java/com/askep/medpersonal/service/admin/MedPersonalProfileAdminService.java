package com.askep.medpersonal.service.admin;


import com.askep.medpersonal.dto.admin.MedPersonaProfileAdminRequest;
import com.askep.medpersonal.dto.admin.MedPersonalProfileAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface MedPersonalProfileAdminService {

    Page<MedPersonalProfileAdminResponse> getAllMedPersonalProfiles(Pageable pageable);

    Page<MedPersonalProfileAdminResponse> searchMedPersonalBy(
            Pageable pageable, String searchText);

    MedPersonalProfileAdminResponse getMedPersonalProfile(String medPersonalEmail);

    MedPersonalProfileAdminResponse createProfile(
            MedPersonaProfileAdminRequest medPersonaProfileAdminRequest);

    MedPersonalProfileAdminResponse updateProfile(
            MedPersonaProfileAdminRequest medPersonaProfileAdminRequest);

    void deleteMedPersonalProfile(String medPersonalEmail, Long medPersonalVersion);

}
