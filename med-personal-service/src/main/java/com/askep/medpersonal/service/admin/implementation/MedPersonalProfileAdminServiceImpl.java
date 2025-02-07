package com.askep.medpersonal.service.admin.implementation;

import com.askep.medpersonal.config.PropertyConfig;
import com.askep.medpersonal.dto.admin.MedPersonaProfileAdminRequest;
import com.askep.medpersonal.dto.admin.MedPersonalProfileAdminResponse;
import com.askep.medpersonal.dto.file.FileDto;
import com.askep.medpersonal.entity.MedPersonalProfileEntity;
import com.askep.medpersonal.exception.exceptions.MedPersonalPhotoError;
import com.askep.medpersonal.exception.exceptions.MedPersonalProfileNotFoundException;
import com.askep.medpersonal.exception.exceptions.MedPersonalProfileVersionNotValidException;
import com.askep.medpersonal.mapper.MedPersonalAdminMapper;
import com.askep.medpersonal.repository.MedPersonalRepository;
import com.askep.medpersonal.service.admin.MedPersonalProfileAdminService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static com.askep.medpersonal.mapper.MedPersonalAdminMapper.*;
import static com.askep.medpersonal.utils.file.FileUtil.*;
import static com.askep.medpersonal.utils.file.RandomFileNameGenerator.generateRandomFileName;
import static com.askep.medpersonal.utils.security.GetUserFromCurrentAuthSession.getUserEmailFromCurrentSession;

@Service
@RequiredArgsConstructor
public class MedPersonalProfileAdminServiceImpl implements MedPersonalProfileAdminService {

    private final MedPersonalRepository medPersonalRepository;

    private final PropertyConfig propertyConfig;

    @Override
    public Page<MedPersonalProfileAdminResponse> getAllMedPersonalProfiles(Pageable pageable) {
        Page<MedPersonalProfileEntity> medPersonalProfileEntities = medPersonalRepository.findAll(pageable);
        return getMedPersonalProfileAdminResponses(medPersonalProfileEntities);
    }

    @Override
    public Page<MedPersonalProfileAdminResponse> searchMedPersonalBy(Pageable pageable, String searchText) {
        Page<MedPersonalProfileEntity> medPersonalProfileEntities = medPersonalRepository
                .searchByText(pageable, searchText);
        return getMedPersonalProfileAdminResponses(medPersonalProfileEntities);
    }

    @NotNull
    private Page<MedPersonalProfileAdminResponse> getMedPersonalProfileAdminResponses(
            Page<MedPersonalProfileEntity> medPersonalProfileEntities) {
        medPersonalProfileEntities.forEach(
                profile -> {
                    try {
                        if (profile.getPhotoUrl().equals(propertyConfig.getFilePath())) {
                            profile.setPhotoUrl(encodeFile(profile.getPhotoUrl()));
                        }
                    } catch (IOException e) {
                        throw new MedPersonalPhotoError("Photo file error!");
                    }
                }
        );
        return medPersonalProfileEntities.map(MedPersonalAdminMapper::mapToProfileAdminResponse);
    }

    @Override
    @Transactional
    public MedPersonalProfileAdminResponse getMedPersonalProfile(String medPersonalEmail) throws IOException {
        MedPersonalProfileEntity medPersonalProfileEntity = medPersonalRepository
                .findByEmailIgnoreCase(medPersonalEmail)
                .orElseThrow(
                        () -> new MedPersonalProfileNotFoundException(
                                "Can not find personal profile by email: " +
                                        medPersonalEmail + "!"
                        )
                );

        if (medPersonalProfileEntity.getPhotoUrl().equals(propertyConfig.getFilePath())) {
            medPersonalProfileEntity.setPhotoUrl(encodeFile(medPersonalProfileEntity.getPhotoUrl()));
        }

        return mapToProfileAdminResponse(medPersonalProfileEntity);
    }

    @Override
    public MedPersonalProfileAdminResponse createProfile(
            MedPersonaProfileAdminRequest medPersonaProfileAdminRequest) {
        MedPersonalProfileEntity medPersonalProfileEntity =  mapProfileAdminRequestToEntity(
                medPersonaProfileAdminRequest);
        medPersonalProfileEntity.setPhotoUrl(propertyConfig.getFilePath());
        medPersonalRepository.save(medPersonalProfileEntity);
        return mapToProfileAdminResponse(medPersonalProfileEntity);
    }

    @Override
    @Transactional
    public MedPersonalProfileAdminResponse updateProfile(
            MedPersonaProfileAdminRequest medPersonaProfileAdminRequest) {
        MedPersonalProfileEntity medPersonalProfileEntity = medPersonalRepository
                .findByEmailIgnoreCase(medPersonaProfileAdminRequest.getEmail())
                .orElseThrow(
                        () -> new MedPersonalProfileNotFoundException(
                                "Can not find personal profile by email: " +
                                        medPersonaProfileAdminRequest.getEmail() + "!"
                        )
                );

        if (!medPersonalProfileEntity.getVersion().equals(medPersonaProfileAdminRequest.getVersion())) {
            throw new MedPersonalProfileVersionNotValidException(
                    "Current version of med personal profile entity version not valid!"
            );
        }

        medPersonalProfileEntity = mapProfileAdminRequestToEntity(medPersonaProfileAdminRequest);
        medPersonalProfileEntity.setPhotoUrl(propertyConfig.getFilePath());

        medPersonalRepository.save(medPersonalProfileEntity);

        return mapToProfileAdminResponse(medPersonalProfileEntity);
    }

    @Override
    @Transactional
    @Async("fileExecutor")
    public CompletableFuture<MedPersonalProfileAdminResponse> createProfilePhoto(
            FileDto fileDto, String profileEmail) throws IOException {

        MedPersonalProfileEntity medPersonalProfileEntity = medPersonalRepository
                .findByEmailIgnoreCase(profileEmail)
                .orElseThrow(
                        () -> new MedPersonalProfileNotFoundException(
                                "Can not find personal profile by email: " + profileEmail + "!"
                        )
                );

        String decodedFile = decodeFile(
                generateRandomFileName(), fileDto.getEncodedFile()
        );

        medPersonalProfileEntity.setPhotoUrl(
                medPersonalProfileEntity.getPhotoUrl().concat(decodedFile)
        );

        medPersonalRepository.save(medPersonalProfileEntity);

        writeFile(
                generateRandomFileName(),
                propertyConfig.getFilePath(),
                fileDto.getEncodedFile()
        );

        return CompletableFuture.completedFuture(
                mapToProfileAdminResponse(medPersonalProfileEntity)
        );
    }

    @Override
    @Transactional
    @Async("fileExecutor")
    public CompletableFuture<MedPersonalProfileAdminResponse> deleteProfilePhoto(
            String profileEmail) throws IOException {

        MedPersonalProfileEntity medPersonalProfileEntity = medPersonalRepository
                .findByEmailIgnoreCase(profileEmail)
                .orElseThrow(
                        () -> new MedPersonalProfileNotFoundException(
                                "Can not find personal profile by email: " + profileEmail + "!"
                        )
                );

        deleteFile(medPersonalProfileEntity.getPhotoUrl());
        medPersonalProfileEntity.setPhotoUrl("");
        medPersonalRepository.save(medPersonalProfileEntity);

        return CompletableFuture.completedFuture(
                mapToProfileAdminResponse(medPersonalProfileEntity)
        );
    }

    @Override
    @Transactional
    public void deleteMedPersonalProfile(
            String medPersonalEmail, Long medPersonalVersion) {
        if (!medPersonalRepository.existsByEmailIgnoreCase(medPersonalEmail)) {
            throw new MedPersonalProfileNotFoundException(
                    "Can not find personal profile by email: " + medPersonalEmail + "!"
            );
        }
        if (!medPersonalRepository.existsByVersion(medPersonalVersion)) {
            throw new MedPersonalProfileVersionNotValidException(
                    "Current version of med personal profile entity version not valid!"
            );
        }
        medPersonalRepository.deleteByEmailIgnoreCase(getUserEmailFromCurrentSession());
    }

}
