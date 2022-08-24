package com.libtask.library2.services;

import com.libtask.library2.entities.RegistrationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegistrationServiceTest {

    @Autowired
    RegistrationService registrationService;
    @Autowired
    UserService userService;

    @Test
    void itShouldRegisterNewUser() {
        //given
        RegistrationRequest request = new RegistrationRequest(
                "Test",
                "Test",
                "Test",
                "Test");

        //when
        registrationService.register(request);

        //then
        assertThat(userService.showAllUsers().contains(userService.getUserByEmail("Test"))).isTrue();
        userService.deleteUser(userService.getUserByEmail("Test"));
    }
}