package com.libtask.library2.controllers;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.Genre;
import com.libtask.library2.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {
    private BookRepository bookRepository;

    @GetMapping("/add")
    public String addBook(Model model) {
        return "add-book";
    }

    @PostMapping("/add")
    public String addBookPost(@RequestBody String id,
                              String name,
                              String author,
                              String genre,
                              Model model) {
        Book book = new Book(id, name, author, Genre.valueOf(genre), null);
        bookRepository.save(book);
        return "redirect:/books/catalog";
    }


    @GetMapping("/catalog")
    public String catalog(Model model) {
        return String.valueOf(bookRepository.findAll());
    }

    @GetMapping("/{id}")
    public String bookInfo(@PathVariable(value = "id") String id, Model model) {
        return bookRepository.findById(id).toString();
    }

}
