package com.askep.doctor.controller;

import com.askep.doctor.service.client.DoctorProfileClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctor-service/client")
public class DoctorProfileClientController {

    private final DoctorProfileClientService doctorProfileClientService;

}
