package com.libtask.library2.services;

import com.libtask.library2.entities.User;
import com.libtask.library2.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookServiceTest {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    @Autowired
    BookRepository bookRepository;


    @Test
    void itShouldGetCatalog() {

        assertEquals(
                bookRepository.findAll(Pageable.unpaged()),
                bookService.getCatalog(Pageable.unpaged()));
    }

    @Test
    void itShouldGetBookById() {

        assertEquals(
                bookService.getBookById(1L),
                bookRepository.findById(1L).orElseThrow());
    }

    @Test
    void itShouldGetFreeBooks() {

        assertEquals(
                bookService.getFreeBooks(Pageable.unpaged()),
                bookRepository.findFreeBooks(Pageable.unpaged()));
    }

    @Test
    void itShouldGetTakenBooksByUserId() {

        User user = userService.getUserById(1L);
        assertEquals(
                bookService.getTakenBooksByUserId(user.getId(), Pageable.unpaged()),
                bookRepository.findTakenBooksByUserId(user.getId(), Pageable.unpaged()));
    }
}