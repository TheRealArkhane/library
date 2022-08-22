package com.libtask.library2.controllers;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.BookDto;
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
        return bookService.showCatalog();
    }

    @GetMapping("/{id}")
    public Book bookInfo(@PathVariable(value = "id") Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/delete")
    public void deleteBook(@RequestParam Long id) {
        bookService.deleteBook(bookService.getBookById(id));
    }
}
