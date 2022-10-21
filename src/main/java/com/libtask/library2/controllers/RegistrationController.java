package com.libtask.library2.controllers;

import com.libtask.library2.dto.RegistrationRequest;
import com.libtask.library2.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping
    public void register() {
    }

    @PostMapping
    public Long register(@RequestBody RegistrationRequest request) {
       return registrationService.register(request).getId();
    }
}
