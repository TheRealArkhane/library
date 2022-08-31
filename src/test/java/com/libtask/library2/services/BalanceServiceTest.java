package com.libtask.library2.services;

import com.libtask.library2.dto.BookDto;
import com.libtask.library2.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void itShouldAddBookToUserBalance() {
        //given
        User user = new User(
                "Test",
                "Test",
                "Test",
                "test",
                Role.USER);
        registrationService.signUpUser(user);

        Book book = bookService.addBook(
                new BookDto(
                        "Test",
                        "Test",
                        "Test",
                        Genre.OTHER));
        //when
        balanceService.addBookToUserBalance(
                userService.getUserByEmail(user.getEmail()),
                bookService.getBookByIsbn(book.getIsbn()));

        //then
        assertThat(bookService.getBookByIsbn(book.getIsbn()).getUserId()
                .equals(userService.getUserByEmail(user.getEmail()).getId()))
                .isTrue();

        balanceService.removeBookFromUserBalance(
                userService.getUserByEmail(user.getEmail()),
                bookService.getBookByIsbn(book.getIsbn()));

        bookService.deleteBook(bookService.getBookByIsbn(book.getIsbn()).getId());
        userService.deleteUser(userService.getUserByEmail(user.getEmail()));
    }

    @Test
    void itShouldRemoveBookFromUserBalance() {
        //given
        User user = new User(
                "Test",
                "Test",
                "Test",
                "test",
                Role.USER);
        registrationService.signUpUser(user);

        Book book = bookService.addBook(
                new BookDto(
                        "Test",
                        "Test",
                        "Test",
                        Genre.OTHER));
        //when
        balanceService.addBookToUserBalance(
                userService.getUserByEmail(user.getEmail()),
                bookService.getBookByIsbn(book.getIsbn()));

        balanceService.removeBookFromUserBalance(
                userService.getUserByEmail(user.getEmail()),
                bookService.getBookByIsbn(book.getIsbn()));
        //then
        assertThat(bookService.getBookByIsbn(book.getIsbn()).getUserId() == null).isTrue();

        bookService.deleteBook(bookService.getBookByIsbn(book.getIsbn()).getId());
        userService.deleteUser(userService.getUserByEmail(user.getEmail()));
    }
}