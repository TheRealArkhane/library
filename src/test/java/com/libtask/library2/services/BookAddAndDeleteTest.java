package com.libtask.library2.services;

import com.libtask.library2.Library2ApplicationTests;
import com.libtask.library2.dto.BookDto;
import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.Genre;
import com.libtask.library2.repositories.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class BookAddAndDeleteTest extends Library2ApplicationTests {

    /*
    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;
    Book book;

    @BeforeEach
    public void initializeBook() {
        book = bookService.addBook(
                new BookDto(
                        "Test",
                        "Test",
                        "Test",
                        Genre.OTHER));
    }

    @Test
    void itShouldAddBookToDb() {
        assertEquals(bookRepository.getBookByIsbn(book.getIsbn()).orElseThrow(), book);
    }


    @Test
    void itShouldDeleteBookFromDb() {
        bookService.deleteBook(book.getId());
        assertTrue(bookRepository.getBookByIsbn(book.getIsbn()).isEmpty());
    }

    @AfterEach
    public void deleteBook() {
        bookRepository.delete(book);
    }

     */
}
