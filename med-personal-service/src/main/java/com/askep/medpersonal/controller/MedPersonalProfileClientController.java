package com.askep.medpersonal.controller;

import com.askep.medpersonal.dto.client.MedPersonalProfileClientRequest;
import com.askep.medpersonal.dto.client.MedPersonalProfileClientResponse;
import com.askep.medpersonal.dto.file.FileDto;
import com.askep.medpersonal.service.client.MedPersonalProfileClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/personal-service/client")
public class MedPersonalProfileClientController {

    private final MedPersonalProfileClientService medPersonalProfileClientService;


    @GetMapping("/get-profile")
    @ResponseStatus(HttpStatus.OK)
    public MedPersonalProfileClientResponse getProfile() throws IOException {
        return medPersonalProfileClientService.getMedPersonalProfile();
    }

    @PostMapping("/create-profile")
    @ResponseStatus(HttpStatus.CREATED)
    public MedPersonalProfileClientResponse createProfile(
            @Valid @RequestBody MedPersonalProfileClientRequest medPersonalProfileClientRequest) {
        return medPersonalProfileClientService
                .createProfile(medPersonalProfileClientRequest);
    }

    @PutMapping("/update-profile")
    @ResponseStatus(HttpStatus.CREATED)
    public MedPersonalProfileClientResponse updateProfile(
            @Valid @RequestBody MedPersonalProfileClientRequest medPersonalProfileClientRequest) {
        return medPersonalProfileClientService
                .updateProfile(medPersonalProfileClientRequest);
    }

    @PostMapping("/create-photo")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<MedPersonalProfileClientResponse> createPhoto(
            @Valid @RequestBody FileDto fileDto) throws IOException {
        return medPersonalProfileClientService.createProfileImg(fileDto);
    }

    @DeleteMapping("/delete-photo")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<MedPersonalProfileClientResponse> deletePhoto() throws IOException {
        return medPersonalProfileClientService.deleteProfileImg();
    }

    @DeleteMapping("/delete-profile")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deletePProfile(
            @Valid @RequestParam(value = "medPersonalProfileVersion)") Long medPersonalProfileVersion) {
        medPersonalProfileClientService.deleteProfile(medPersonalProfileVersion);
        return new ResponseEntity<>(
                "Profile successful deleted!", HttpStatus.OK);
    }

}
