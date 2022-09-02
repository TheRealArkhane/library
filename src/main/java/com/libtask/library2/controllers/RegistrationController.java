package com.libtask.library2.controllers;

import com.libtask.library2.dto.RegistrationRequest;
import com.libtask.library2.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping(path = "/registration")
    public void register() {
    }

    @PostMapping(path = "/registration")
    public Long register(@RequestBody RegistrationRequest request) {
       return registrationService.register(request).getId();
    }

}
