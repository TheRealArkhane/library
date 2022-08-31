package com.libtask.library2.services;

import com.libtask.library2.dto.BookDto;
import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BookServiceTest {

    @Autowired
    BookService bookService;


    @Test
    void itShouldAddBookToDb() {
        //when
        Book book = bookService.addBook(
                new BookDto(
                        "Test",
                        "Test",
                        "Test",
                        Genre.OTHER));

        //then
        assertTrue(bookService.getCatalog().contains(bookService.getBookByIsbn(book.getIsbn())));
        bookService.deleteBook(bookService.getBookByIsbn(book.getIsbn()).getId());
    }

    @Test
    void itShouldDeleteBookFromDb() {
        //given
        Book book = bookService.addBook(
                new BookDto(
                        "Test",
                        "Test",
                        "Test",
                        Genre.OTHER));

        //when
        Book testBook = bookService.getBookByIsbn(book.getIsbn());
        bookService.deleteBook(testBook.getId());

        //then
        assertFalse(bookService.getCatalog().contains(testBook));
    }
}