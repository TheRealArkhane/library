package com.libtask.library2.controllers;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.User;
import com.libtask.library2.services.BookService;
import com.libtask.library2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    private final BookService bookService;

    @GetMapping("/all")
    public List<User> showAllUsers() {
        return userService.showAllUsers();
    }

    @GetMapping("/{id}")
    public User showUserInformation(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/balance")
    public List<Book> showUserBalance(@PathVariable(value = "id") Long id) {
        return bookService.getTakenBooksListByUserId(id);
    }

}

