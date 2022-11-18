package com.libtask.library2.services;

import com.libtask.library2.Library2ApplicationTests;
import com.libtask.library2.dto.BookDto;
import com.libtask.library2.dto.RegistrationRequest;
import com.libtask.library2.entities.Genre;
import com.libtask.library2.repositories.BookRepository;
import com.libtask.library2.repositories.UserRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class BalanceServiceTest extends Library2ApplicationTests {

    @Autowired
    BalanceService balanceService;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RegistrationService registrationService;
    @Autowired
    BookService bookService;

    private Long testBookId;
    private Long testUserId;

    @BeforeEach
    void createTestBookAndTestUser() {
        testBookId = bookService.addBook(
                new BookDto(
                        "test",
                        "test",
                        "test",
                        Genre.OTHER
                        )).getId();

        testUserId = registrationService.register(
                new RegistrationRequest(
                        "test",
                        "test",
                        "test",
                        "test"
                )).getId();
    }

    @Disabled
    @Test
    void contextLoads() {
    }

    @Test
    void itShouldAddBookToUserBalance() {
        balanceService.addBookToUserBalance(testUserId, testBookId);
        assertEquals(
                testUserId,
                bookRepository.findById(
                        testBookId).orElseThrow(
                                () ->new IllegalArgumentException("Book with this ID is not exist")).getUserId());
    }

    @Test
    void itShouldRemoveBookFromUserBalance() {
        balanceService.addBookToUserBalance(testUserId, testBookId);
        balanceService.removeBookFromUserBalance(testUserId, testBookId);
        assertNull(bookRepository.findById(
                testBookId).orElseThrow(
                () ->new IllegalArgumentException("Book with this ID is not exist")).getUserId());
    }

    @AfterEach
    void deleteTestBookAndTestUser() {
        bookRepository.delete(bookRepository.findById(testBookId).orElseThrow(
                () ->new IllegalArgumentException("Book with this ID is not exist")));
        userRepository.delete(userRepository.findById(testUserId).orElseThrow(
                () ->new IllegalArgumentException("User with this ID is not exist")));
    }
}