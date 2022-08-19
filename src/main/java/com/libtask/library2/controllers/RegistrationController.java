package com.libtask.library2.controllers;

import com.libtask.library2.entities.RegistrationRequest;
import com.libtask.library2.entities.User;
import com.libtask.library2.services.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping(path = "/reg")
    public String register() {
        return "let's sign up!";
    }

    @PostMapping(path = "/reg")
    public User register(@RequestBody RegistrationRequest request) {
       return registrationService.register(request);
    }

}
