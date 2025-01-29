package com.askep.medpersonal.service.admin;


import com.askep.medpersonal.dto.admin.MedPersonalProfileAdminResponse;
import com.askep.medpersonal.dto.client.MedPersonalProfileClientRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface MedPersonalProfileAdminService {

    Page<MedPersonalProfileAdminResponse> getAllMedPersonalProfiles(Pageable pageable);

    Page<MedPersonalProfileAdminResponse> searchMedPersonalBy(
            Pageable pageable, String searchText);

    MedPersonalProfileAdminResponse getMedPersonalProfile(String medPersonalEmail);



    void deleteMedPersonalProfile(String medPersonalEmail, Long medPersonalVersion);

}
