package com.libtask.library2.controllers;

import com.libtask.library2.entities.Book;
import com.libtask.library2.dto.BookDto;
import com.libtask.library2.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @PostMapping("/add")
    public Book addBookPost(@RequestBody BookDto bookDto) {
       return bookService.addBook(bookDto);
    }

    @GetMapping("/catalog")
    public List<Book> getCatalog() {
        return bookService.getCatalog();
    }

    @GetMapping("/catalog/sorted-by-name")
    public List<Book> getCatalogSortedByName() {
        return bookService.getCatalogSortedByName();
    }

    @GetMapping("/catalog/sorted-by-author")
    public List<Book> getCatalogSortedByAuthor() {
        return bookService.getCatalogSortedByAuthor();
    }

    @GetMapping("/catalog/sorted-by-genre")
    public List<Book> getCatalogSortedByGenre() {
        return bookService.getCatalogSortedByGenre();
    }

    @GetMapping("/free")
    public List<Book> getFreeBooks() {
        return bookService.getFreeBooks();
    }

    @GetMapping("/{id}")
    public BookDto bookInfo(@PathVariable(value = "id") Long id) {
        return bookService.bookToBookDto(bookService.getBookById(id));
    }

    @PutMapping("/delete")
    public void deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
    }
}
