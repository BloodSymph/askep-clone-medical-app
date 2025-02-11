package com.askep.medpersonal.service.client;


import com.askep.medpersonal.dto.client.MedPersonalProfileClientRequest;
import com.askep.medpersonal.dto.client.MedPersonalProfileClientResponse;
import com.askep.medpersonal.dto.file.FileDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public interface MedPersonalProfileClientService {

    MedPersonalProfileClientResponse getMedPersonalProfile() throws IOException;

    MedPersonalProfileClientResponse createProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest);
    MedPersonalProfileClientResponse updateProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest);

    CompletableFuture<MedPersonalProfileClientResponse> createProfileImg(
            FileDto fileDto) throws IOException;

    CompletableFuture<MedPersonalProfileClientResponse> deleteProfileImg() throws IOException;

    void deleteProfile(Long medPersonalProfileVersion);

}
