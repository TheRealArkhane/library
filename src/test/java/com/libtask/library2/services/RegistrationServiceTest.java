package com.libtask.library2.services;

import com.libtask.library2.Library2ApplicationTests;
import com.libtask.library2.dto.RegistrationRequest;
import com.libtask.library2.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;


class RegistrationServiceTest extends Library2ApplicationTests {

    @Autowired
    RegistrationService registrationService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    RegistrationRequest request;

    /*

    @BeforeEach
    public void initializeRequest() {

        request = new RegistrationRequest(
                "Test",
                "Test",
                "Test",
                "Test");
    }

    @Test
    void itShouldRegisterNewUser() {

        registrationService.register(request);
        assertTrue(userRepository.findAll().contains(userService.getUserByEmail("Test")));
    }

    @AfterEach
    public void deleteUser() {

        userRepository.delete(userService.getUserByEmail("Test"));
    }
     */
}