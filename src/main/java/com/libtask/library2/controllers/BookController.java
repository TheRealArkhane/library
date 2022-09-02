package com.libtask.library2.controllers;

import com.libtask.library2.entities.Book;
import com.libtask.library2.dto.BookDto;
import com.libtask.library2.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public Page<Book> getCatalog(@PageableDefault(sort = "id", size = 5) Pageable page) {
        return bookService.getCatalog(page);
    }

    @GetMapping("/catalog/sorted-by-name")
    public Page<Book> getCatalogSortedByName(@PageableDefault(sort = "name", size = 5) Pageable page) {
        return bookService.getCatalog(page);
    }

    @GetMapping("/catalog/sorted-by-author")
    public Page<Book> getCatalogSortedByAuthor(@PageableDefault(sort = "author", size = 5) Pageable page) {
        return bookService.getCatalog(page);
    }

    @GetMapping("/catalog/sorted-by-genre")
    public Page<Book> getCatalogSortedByGenre(@PageableDefault(sort = "genre", size = 5) Pageable page) {
        return bookService.getCatalog(page);
    }

    @GetMapping("/free")
    public Page<Book> getFreeBooks(@PageableDefault(sort = "id", size = 5) Pageable page) {
        return bookService.getFreeBooks(page);
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
