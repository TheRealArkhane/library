package com.libtask.library2.services;

import com.libtask.library2.config.WebSecurityConfig;
import com.libtask.library2.entities.RegistrationRequest;
import com.libtask.library2.entities.Role;
import com.libtask.library2.entities.User;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    public String register(RegistrationRequest request) {
        new User (
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                Role.USER
        );
        return "works";
    }
}
