package com.askep.medpersonal.controller;

import com.askep.medpersonal.service.admin.MedPersonalProfileAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctor-service/admin")
public class MedPersonalProfileAdminController {

    private final MedPersonalProfileAdminService medPersonalProfileAdminService;

}
