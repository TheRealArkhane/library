package com.libtask.library2.services;

import com.libtask.library2.entities.RegistrationRequest;
import com.libtask.library2.entities.Role;
import com.libtask.library2.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserService userService;

    public User register(RegistrationRequest request) {
        User newUser = new User (
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                Role.USER
        );
        userService.signUpUser(newUser);
        return newUser;
    }
}
