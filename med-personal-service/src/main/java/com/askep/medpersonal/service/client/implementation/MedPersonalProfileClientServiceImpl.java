package com.askep.medpersonal.service.client.implementation;

import com.askep.medpersonal.dto.client.MedPersonalProfileClientRequest;
import com.askep.medpersonal.dto.client.MedPersonalProfileClientResponse;
import com.askep.medpersonal.entity.MedPersonalProfileEntity;
import com.askep.medpersonal.exception.exceptions.MedPersonalProfileNotFoundException;
import com.askep.medpersonal.exception.exceptions.MedPersonalProfileVersionNotValidException;
import com.askep.medpersonal.repository.MedPersonalRepository;
import com.askep.medpersonal.service.client.MedPersonalProfileClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.askep.medpersonal.mapper.MedPersonalClientMapper.*;
import static com.askep.medpersonal.utils.security.GetUserFromCurrentAuthSession.getUserEmailFromCurrentSession;

@Service
@RequiredArgsConstructor
public class MedPersonalProfileClientServiceImpl implements MedPersonalProfileClientService {

    private final MedPersonalRepository medPersonalRepository;

    @Override
    public MedPersonalProfileClientResponse getDoctorProfile() {
        MedPersonalProfileEntity medPersonalProfileEntity = medPersonalRepository
                .findByEmailIgnoreCase(getUserEmailFromCurrentSession())
                .orElseThrow(
                        () -> new MedPersonalProfileNotFoundException(
                                "Can not find personal profile by email: " +
                                        getUserEmailFromCurrentSession() + "!"
                        )
                );
        return mapToMedPersonalClientResponse(medPersonalProfileEntity);
    }

    @Override
    public MedPersonalProfileClientResponse createDoctorProfile(
            MedPersonalProfileClientRequest medPersonalProfileClientRequest) {
            MedPersonalProfileEntity medPersonalProfileEntity = mapMedPersonalProfileRequestToEntity(
                    medPersonalProfileClientRequest);
            medPersonalProfileEntity.setEmail(getUserEmailFromCurrentSession());
            medPersonalRepository.save(medPersonalProfileEntity);
        return mapToMedPersonalClientResponse(medPersonalProfileEntity);
    }

    @Override
    @Transactional
    public MedPersonalProfileClientResponse updateDoctorProfile(
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
    @Transactional
    public void deleteDoctorProfile(
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
