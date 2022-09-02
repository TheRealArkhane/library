package com.libtask.library2.services;

import com.libtask.library2.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {

    @Autowired
    RegistrationService registrationService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void itShouldShowAllUsers() {

        assertEquals(
                userService.showAllUsers(Pageable.unpaged()),
                userRepository.findAll(Pageable.unpaged()));
    }

    @Test
    void itShouldGetUserById() {

        assertEquals(
                userService.getUserById(1L),
                userRepository.findById(1L).orElseThrow());
    }
}