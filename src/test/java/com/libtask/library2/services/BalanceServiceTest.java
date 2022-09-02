package com.libtask.library2.services;

import com.libtask.library2.dto.BookDto;
import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.Genre;
import com.libtask.library2.entities.Role;
import com.libtask.library2.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class BalanceServiceTest {

    @Autowired
    BalanceService balanceService;
    @Autowired
    UserService userService;
    @Autowired
    RegistrationService registrationService;
    @Autowired
    BookService bookService;

    User user;
    Book book;

    @BeforeEach
    public void initializeTestingObjectsAndTakeBookToUserBalance() {

        user = new User(
                "Test",
                "Test",
                "Test",
                "test",
                Role.USER);
        registrationService.signUpUser(user);

        book = bookService.addBook(
                new BookDto(
                        "Test",
                        "Test",
                        "Test",
                        Genre.OTHER));

        balanceService.addBookToUserBalance(
                userService.getUserByEmail(user.getEmail()),
                bookService.getBookByIsbn(book.getIsbn()));
    }

    @Test
    void itShouldAddBookToUserBalance() {

        assertEquals(
                bookService.getBookByIsbn(book.getIsbn()).getUserId(),
                userService.getUserByEmail(user.getEmail()).getId());
    }

    @Test
    void itShouldRemoveBookFromUserBalance() {

        balanceService.removeBookFromUserBalance(
                userService.getUserByEmail(user.getEmail()),
                bookService.getBookByIsbn(book.getIsbn()));

        assertNull(bookService.getBookByIsbn(book.getIsbn()).getUserId());
    }

    @AfterEach
    public void deleteTestingObjects() {

        if (bookService.getBookByIsbn(book.getIsbn()).getUserId() != null) {
        balanceService.removeBookFromUserBalance(
                userService.getUserByEmail(user.getEmail()),
                bookService.getBookByIsbn(book.getIsbn()));
        }
        bookService.deleteBook(bookService.getBookByIsbn(book.getIsbn()).getId());
        userService.deleteUser(userService.getUserByEmail(user.getEmail()));
    }
}