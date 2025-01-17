package com.askep.doctor.service.client.implementation;

import com.askep.doctor.repository.DoctorProfileRepository;
import com.askep.doctor.service.client.DoctorProfileClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorProfileClientServiceImpl implements DoctorProfileClientService {

    private final DoctorProfileRepository doctorProfileRepository;

}
