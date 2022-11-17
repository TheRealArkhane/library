package com.libtask.library2.services;

import com.libtask.library2.Library2ApplicationTests;
import com.libtask.library2.dto.BookDto;
import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.Genre;
import com.libtask.library2.repositories.BookRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BookServiceTest extends Library2ApplicationTests {
    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;

    @Disabled
    @Test
    void contextLoads() {
    }

    @Test
    void itShouldAddBookToDb() {
        Book testBook = bookService.addBook(
                new BookDto(
                        "test",
                        "test",
                        "test",
                        Genre.OTHER
                ));
        assertTrue(bookRepository.existsById(testBook.getId()));
        bookRepository.delete(testBook);
    }

    @Test
    void itShouldGetBooksSortedByCriterion() {
        assertEquals(
                bookRepository.findAll(Sort.by("name")),
                bookService.getBooksSortedByCriterion(
                        "name", PageRequest.ofSize((int) bookRepository.count())).getContent());
    }
}