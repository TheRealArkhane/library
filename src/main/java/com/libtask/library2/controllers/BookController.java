package com.libtask.library2.controllers;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.Genre;
import com.libtask.library2.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("/add")
    public void addBook() {
        //необходима реализация фронта
    }

    @PostMapping("/add")
    public void addBookPost(@RequestBody String isbn, String name, String author, Genre genre) {
        bookService.addBook(isbn, name, author, genre);
    }


    @GetMapping("/catalog")
    public List<Book> catalog() {
        return bookService.showCatalog();
    }

    @GetMapping("/{id}")
    public Book bookInfo(@PathVariable(value = "id") int id) {
        return bookService.getBookById(id);
    }

}
