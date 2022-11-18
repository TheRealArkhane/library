package com.libtask.library2.controllers;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.User;
import com.libtask.library2.repositories.BookRepository;
import com.libtask.library2.services.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;
    private final BookRepository bookRepository;

    @PutMapping("/take")
    public Book addBookToUserBalance (
            @RequestParam Long userId,
            @RequestParam Long bookId) throws IllegalArgumentException {
        balanceService.addBookToUserBalance(userId, bookId);
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book with this ID is not exist"));
    }

    @PutMapping("/return")
    public Book removeBookFromUserBalance(
            @RequestParam Long userId,
            @RequestParam Long bookId) throws IllegalArgumentException {
        balanceService.removeBookFromUserBalance(userId, bookId);
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book with this ID is not exist"));
    }

    @GetMapping("/user")
    public Page<Book> showUserBalance(
            @PageableDefault(sort = "id") Pageable page, Principal user) {
        log.info("{}", user.getName());

        return bookRepository.findTakenBooksByUserId(Long.parseLong(user.getName()), page);
    }
}
