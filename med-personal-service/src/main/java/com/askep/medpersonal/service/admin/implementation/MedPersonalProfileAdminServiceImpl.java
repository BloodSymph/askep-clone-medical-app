package com.askep.medpersonal.service.admin.implementation;

import com.askep.medpersonal.dto.admin.MedPersonalProfileAdminResponse;
import com.askep.medpersonal.dto.client.MedPersonalProfileClientRequest;
import com.askep.medpersonal.entity.MedPersonalProfileEntity;
import com.askep.medpersonal.exception.exceptions.MedPersonalProfileNotFoundException;
import com.askep.medpersonal.exception.exceptions.MedPersonalProfileVersionNotValidException;
import com.askep.medpersonal.mapper.MedPersonalAdminMapper;
import com.askep.medpersonal.repository.MedPersonalRepository;
import com.askep.medpersonal.service.admin.MedPersonalProfileAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.askep.medpersonal.mapper.MedPersonalAdminMapper.mapToDoctorProfileAdminResponse;
import static com.askep.medpersonal.utils.security.GetUserFromCurrentAuthSession.getUserEmailFromCurrentSession;

@Service
@RequiredArgsConstructor
public class MedPersonalProfileAdminServiceImpl implements MedPersonalProfileAdminService {

    private final MedPersonalRepository medPersonalRepository;

    @Override
    public Page<MedPersonalProfileAdminResponse> getAllMedPersonalProfiles(Pageable pageable) {
        return medPersonalRepository
                .findAll(pageable)
                .map(MedPersonalAdminMapper::mapToDoctorProfileAdminResponse);
    }

    @Override
    public Page<MedPersonalProfileAdminResponse> searchMedPersonalBy(Pageable pageable, String searchText) {
        return medPersonalRepository
                .searchByText(pageable, searchText)
                .map(MedPersonalAdminMapper::mapToDoctorProfileAdminResponse);
    }

    @Override
    public MedPersonalProfileAdminResponse getMedPersonalProfile(String medPersonalEmail) {
        MedPersonalProfileEntity medPersonalProfileEntity = medPersonalRepository
                .findByEmailIgnoreCase(medPersonalEmail)
                .orElseThrow(
                        () -> new MedPersonalProfileNotFoundException(
                                "Can not find personal profile by email: " +
                                        getUserEmailFromCurrentSession() + "!"
                        )
                );
        return mapToDoctorProfileAdminResponse(medPersonalProfileEntity);
    }

    @Override
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
