package com.askep.medpersonal.controller;

import com.askep.medpersonal.dto.admin.MedPersonalProfileAdminResponse;
import com.askep.medpersonal.service.admin.MedPersonalProfileAdminService;
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
