package com.libtask.library2.services;

import com.libtask.library2.Library2ApplicationTests;
import com.libtask.library2.dto.RegistrationRequest;
import com.libtask.library2.entities.User;
import com.libtask.library2.repositories.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationServiceTest extends Library2ApplicationTests {
    @Autowired
    RegistrationService registrationService;
    @Autowired
    UserRepository userRepository;

    @Disabled
    @Test
    void contextLoads() {
    }

    @Test
    void itShouldRegisterNewUser() {
        User testUser = registrationService.register(
                new RegistrationRequest(
                        "test",
                        "test",
                        "test",
                        "test"
                ));
        assertTrue(userRepository.existsById(testUser.getId()));
        userRepository.delete(testUser);
    }

}