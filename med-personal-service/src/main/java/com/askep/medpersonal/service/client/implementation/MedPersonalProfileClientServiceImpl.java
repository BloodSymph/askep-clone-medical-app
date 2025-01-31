package com.askep.medpersonal.service.client.implementation;

import com.askep.medpersonal.config.PropertyConfig;
import com.askep.medpersonal.dto.client.MedPersonalProfileClientRequest;
import com.askep.medpersonal.dto.client.MedPersonalProfileClientResponse;
import com.askep.medpersonal.dto.file.FileDto;
import com.askep.medpersonal.entity.MedPersonalProfileEntity;
import com.askep.medpersonal.exception.exceptions.MedPersonalProfileNotFoundException;
import com.askep.medpersonal.exception.exceptions.MedPersonalProfileVersionNotValidException;
import com.askep.medpersonal.repository.MedPersonalRepository;
import com.askep.medpersonal.service.client.MedPersonalProfileClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static com.askep.medpersonal.mapper.MedPersonalClientMapper.*;
import static com.askep.medpersonal.utils.file.FileUtil.encodeFile;
import static com.askep.medpersonal.utils.security.GetUserFromCurrentAuthSession.getUserEmailFromCurrentSession;

@Service
@RequiredArgsConstructor
public class MedPersonalProfileClientServiceImpl implements MedPersonalProfileClientService {

    private final MedPersonalRepository medPersonalRepository;

    private final PropertyConfig propertyConfig;

    @Override
    @Transactional
    public MedPersonalProfileClientResponse getMedPersonalProfile() throws IOException {
        MedPersonalProfileEntity medPersonalProfileEntity = medPersonalRepository
                .findByEmailIgnoreCase(getUserEmailFromCurrentSession())
                .orElseThrow(
                        () -> new MedPersonalProfileNotFoundException(
                                "Can not find personal profile by email: " +
                                        getUserEmailFromCurrentSession() + "!"
                        )
                );

        if (medPersonalProfileEntity.getPhotoUrl().equals(propertyConfig.getFilePath())) {
            medPersonalProfileEntity.setPhotoUrl(encodeFile(medPersonalProfileEntity.getPhotoUrl()));
        }

        return mapToMedPersonalClientResponse(medPersonalProfileEntity);
    }

    @Override
    public MedPersonalProfileClientResponse createProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest) {
            MedPersonalProfileEntity medPersonalProfileEntity = mapMedPersonalProfileRequestToEntity(
                    medPersonalProfileClientRequest);
            medPersonalProfileEntity.setEmail(getUserEmailFromCurrentSession());
            medPersonalProfileEntity.setPhotoUrl(propertyConfig.getFilePath());
            medPersonalRepository.save(medPersonalProfileEntity);
        return mapToMedPersonalClientResponse(medPersonalProfileEntity);
    }

    @Override
    @Transactional
    public MedPersonalProfileClientResponse updateProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest) {
        MedPersonalProfileEntity medPersonalProfileEntity = medPersonalRepository
                .findByEmailIgnoreCase(getUserEmailFromCurrentSession())
                .orElseThrow(
                        () -> new MedPersonalProfileNotFoundException(
                                "Can not find personal profile by email: " +
                                        getUserEmailFromCurrentSession() + "!"
                        )
                );

        if (!medPersonalProfileEntity.getVersion().equals(medPersonalProfileClientRequest.getVersion())) {
            throw new MedPersonalProfileVersionNotValidException(
                    "Current version of med personal profile entity version not valid!"
            );
        }

        medPersonalProfileEntity.setFirstName(medPersonalProfileClientRequest.getFirstName());
        medPersonalProfileEntity.setLastName(medPersonalProfileClientRequest.getLastName());
        medPersonalProfileEntity.setPhoneNumber(medPersonalProfileClientRequest.getPhoneNumber());
        medPersonalProfileEntity.setEmail(getUserEmailFromCurrentSession());
        medPersonalProfileEntity.setAddress(medPersonalProfileClientRequest.getAddress());
        medPersonalProfileEntity.setSpecialization(medPersonalProfileClientRequest.getSpecialization());
        medPersonalRepository.save(medPersonalProfileEntity);
        return mapToMedPersonalClientResponse(medPersonalProfileEntity);
    }

    @Override
    @Async("fileExecutor")
    @Transactional
    public CompletableFuture<MedPersonalProfileClientResponse> createProfileImg(
            FileDto fileDto, String profileEmail) throws IOException {

        return null;
    }

    @Override
    @Async("fileExecutor")
    @Transactional
    public CompletableFuture<MedPersonalProfileClientResponse> deleteProfileImg(
            String profileEmail) throws IOException {
        return null;
    }

    @Override
    @Transactional
    public void deleteProfile(
            Long medPersonalVersion) {
        if (!medPersonalRepository.existsByEmailIgnoreCase(getUserEmailFromCurrentSession())) {
            throw new MedPersonalProfileNotFoundException(
                    "Can not find personal profile by email: " + getUserEmailFromCurrentSession() + "!"
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
