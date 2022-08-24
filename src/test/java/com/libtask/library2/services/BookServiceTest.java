package com.libtask.library2.services;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.BookDto;
import com.libtask.library2.entities.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(bookService.showCatalog().contains(bookService.getBookByIsbn(book.getIsbn()))).isTrue();
        bookService.deleteBook(bookService.getBookByIsbn(book.getIsbn()));
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
        bookService.deleteBook(bookService.getBookByIsbn(book.getIsbn()));

        //then
        assertThat(bookService.showCatalog().contains(bookService.getBookByIsbn(book.getIsbn()))).isFalse();
    }
}