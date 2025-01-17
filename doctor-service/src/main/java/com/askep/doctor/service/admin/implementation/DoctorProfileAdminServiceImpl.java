package com.askep.doctor.service.admin.implementation;

import com.askep.doctor.repository.DoctorProfileRepository;
import com.askep.doctor.service.admin.DoctorProfileAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorProfileAdminServiceImpl implements DoctorProfileAdminService {

    private final DoctorProfileRepository doctorProfileRepository;

}
