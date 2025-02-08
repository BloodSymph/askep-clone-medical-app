package com.askep.medpersonal.service.admin;


import com.askep.medpersonal.dto.admin.MedPersonaProfileAdminRequest;
import com.askep.medpersonal.dto.admin.MedPersonalProfileAdminResponse;
import com.askep.medpersonal.dto.file.FileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public interface MedPersonalProfileAdminService {

    Page<MedPersonalProfileAdminResponse> getAllMedPersonalProfiles(Pageable pageable);

    Page<MedPersonalProfileAdminResponse> searchMedPersonalBy(
            Pageable pageable, String searchText);

    MedPersonalProfileAdminResponse getMedPersonalProfile(String medPersonalEmail) throws IOException;

    MedPersonalProfileAdminResponse createProfile(
            MedPersonaProfileAdminRequest medPersonaProfileAdminRequest);

    MedPersonalProfileAdminResponse updateProfile(
            MedPersonaProfileAdminRequest medPersonaProfileAdminRequest);

    CompletableFuture<MedPersonalProfileAdminResponse> createProfilePhoto(
            FileDto fileDto, String profileEmail) throws IOException;

    CompletableFuture<MedPersonalProfileAdminResponse> deleteProfilePhoto(
            String profileEmail) throws IOException;

    void deleteMedPersonalProfile(String medPersonalEmail, Long medPersonalVersion);

    @Scheduled(fixedRate = 200)
    void evictAllCache();

}
