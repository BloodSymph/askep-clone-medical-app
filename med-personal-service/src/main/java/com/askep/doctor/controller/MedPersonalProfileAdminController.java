package com.askep.doctor.controller;

import com.askep.doctor.service.admin.MedPersonalProfileAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctor-service/admin")
public class MedPersonalProfileAdminController {

    private final MedPersonalProfileAdminService medPersonalProfileAdminService;

}
