package com.libtask.library2.controllers;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.User;
import com.libtask.library2.services.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @PutMapping("/book/take")
    public void takeBook (Book book, User user) {
        balanceService.takeBook(user, book);
    }

    @PutMapping("/book/return")
    public void returnBook (Book book, User user) {
        balanceService.returnBook(user, book);
    }

}
