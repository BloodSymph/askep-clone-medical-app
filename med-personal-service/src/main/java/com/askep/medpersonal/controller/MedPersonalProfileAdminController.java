package com.askep.medpersonal.controller;

import com.askep.medpersonal.dto.admin.MedPersonaProfileAdminRequest;
import com.askep.medpersonal.dto.admin.MedPersonalProfileAdminResponse;
import com.askep.medpersonal.dto.client.MedPersonalProfileClientRequest;
import com.askep.medpersonal.dto.file.FileDto;
import com.askep.medpersonal.service.admin.MedPersonalProfileAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/personal-service/admin")
public class MedPersonalProfileAdminController {

    private final MedPersonalProfileAdminService medPersonalProfileAdminService;
    @GetMapping("/profiles")
    @ResponseStatus(HttpStatus.OK)
    public Page<MedPersonalProfileAdminResponse> getAllPersonal (
            @PageableDefault(
                    sort = "email",
                    direction = Sort.Direction.ASC,
                    page = 0,
                    size = 20
            ) Pageable pageable) {
        return medPersonalProfileAdminService.getAllMedPersonalProfiles(pageable);
    }

    @GetMapping("/search-profile")
    @ResponseStatus(HttpStatus.OK)
    public Page<MedPersonalProfileAdminResponse> searchProfile(
           @PageableDefault(
                   sort = "email",
                   direction = Sort.Direction.ASC,
                   page = 0,
                   size = 20
           ) Pageable pageable, String searchText) {
        return medPersonalProfileAdminService.searchMedPersonalBy(pageable, searchText);
    }

    @GetMapping("/profiles/{profile-email}")
    @ResponseStatus(HttpStatus.OK)
    public MedPersonalProfileAdminResponse getProfileByEmail(
            @PathVariable (value = "profile-email") String profileEmail) throws IOException {
        return medPersonalProfileAdminService.getMedPersonalProfile(profileEmail);
    }

    @PostMapping("/profiles/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MedPersonalProfileAdminResponse createProfile(
            @Valid @RequestBody MedPersonaProfileAdminRequest medPersonaProfileAdminRequest) {
        return medPersonalProfileAdminService.createProfile(medPersonaProfileAdminRequest);
    }

    @PutMapping("/profiles/update")
    @ResponseStatus(HttpStatus.CREATED)
    public MedPersonalProfileAdminResponse updateProfile(
            @Valid @RequestBody MedPersonaProfileAdminRequest medPersonaProfileAdminRequest) {
        return medPersonalProfileAdminService.updateProfile(medPersonaProfileAdminRequest);
    }

    @PostMapping("/profile/{profileEmail}/create-photo")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<MedPersonalProfileAdminResponse> createPhoto(
            @Valid @RequestBody FileDto fileDto,
            @PathVariable(value = "profileEmail") String profileEmail) throws IOException {
        return medPersonalProfileAdminService.createProfilePhoto(fileDto, profileEmail);
    }

    @DeleteMapping("/profile/{profileEmail}/delete-photo")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<MedPersonalProfileAdminResponse> deletePhoto(
            @PathVariable(value = "profileEmail") String profileEmail) throws IOException {
        return medPersonalProfileAdminService.deleteProfilePhoto(profileEmail);
    }

    @DeleteMapping("/profiles/{profile-email}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteProfile(
            @PathVariable (value = "profile-email") String profileEmail,
            @RequestParam(value = "profile-version", required = true) Long medPersonalVersion) {
        medPersonalProfileAdminService.deleteMedPersonalProfile(profileEmail, medPersonalVersion);
        return new ResponseEntity<>(
                "DProfile successful deleted!", HttpStatus.OK);
    }

}
