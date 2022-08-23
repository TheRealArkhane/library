package com.libtask.library2.controllers;

import com.libtask.library2.entities.User;
import com.libtask.library2.services.BalanceService;
import com.libtask.library2.services.BookService;
import com.libtask.library2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;
    private final UserService userService;
    private final BookService bookService;

    @PutMapping("/take")
    public User addBookToUserBalance(@RequestParam Long userId, @RequestParam Long bookId) {
        balanceService.addBookToUserBalance(
                userService.getUserById(userId),
                bookService.getBookById(bookId));
        return userService.getUserById(userId);
    }

    @PutMapping("/return")
    public void removeBookFromUserBalance(@RequestParam Long userId, @RequestParam Long bookId) {
        balanceService.removeBookFromUserBalance(
                userService.getUserById(userId),
                bookService.getBookById(bookId));
    }
}
