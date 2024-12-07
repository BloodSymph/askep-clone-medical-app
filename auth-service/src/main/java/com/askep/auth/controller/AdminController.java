package com.askep.auth.controller;

import com.askep.auth.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth-service/admin")
public class AdminController {

    private final AdminService adminService;

}
