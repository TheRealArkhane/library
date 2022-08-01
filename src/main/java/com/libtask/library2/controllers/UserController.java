package com.libtask.library2.controllers;

import com.libtask.library2.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    
    UserRepository userRepository;

    @GetMapping("/all")
    public String showAllUsers (Model model) {
            return String.valueOf(userRepository.findAll());
        }
}

