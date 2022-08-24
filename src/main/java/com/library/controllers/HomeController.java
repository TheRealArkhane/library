package com.library.controllers;

import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@NonNull Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }
}
