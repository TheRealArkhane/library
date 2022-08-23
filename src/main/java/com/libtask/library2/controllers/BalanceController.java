package com.libtask.library2.controllers;

import com.libtask.library2.entities.Book;
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
    public Book addBookToUserBalance(@RequestParam Long userId, @RequestParam Long bookId) {
        balanceService.addBookToUserBalance(
                userService.getUserById(userId),
                bookService.getBookById(bookId));
        return bookService.getBookById(bookId);
    }

    @PutMapping("/return")
    public Book removeBookFromUserBalance(@RequestParam Long userId, @RequestParam Long bookId) {
        balanceService.removeBookFromUserBalance(
                userService.getUserById(userId),
                bookService.getBookById(bookId));
        return bookService.getBookById(bookId);
    }
}
