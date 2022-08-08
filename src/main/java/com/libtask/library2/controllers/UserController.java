package com.libtask.library2.controllers;

import com.libtask.library2.entities.User;
import com.libtask.library2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    
    UserService userService;

    @GetMapping("/all")
    public List<User> showAllUsers () {
        return userService.showAllUsers();
    }
}

