package com.libtask.library2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/books/catalog")
    public String catalogPage() {
        return "catalog";
    }

    @GetMapping("/books/catalog/free")
    public String freeBooksPage() {
        return "catalog-free";
    }

    @GetMapping("/balance")
    public String userBalancePage() {
        return "balance";
    }

}
