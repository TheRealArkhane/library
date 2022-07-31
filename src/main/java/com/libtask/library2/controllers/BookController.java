package com.libtask.library2.controllers;

import com.libtask.library2.entities.Book;
import com.libtask.library2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/add")
    public String addBook(Model model) {
        return "add-book";
    }

    @PostMapping("/add")
    public String addBookPost(@RequestParam String name,
                              @RequestParam String author,
                              //добавлю возможность выбора, а не ввода, от этого исправлю и конструктор сущности
                              @RequestParam String genre, Model model) {
        Book book = new Book(Book.generateISBN(), name, author, genre, null);
        bookRepository.save(book);
        return "redirect:/books/catalog";
    }


    @GetMapping("/catalog")
    public String catalog(Model model) {
        return bookRepository.findAll().toString();
    }

    @GetMapping("/{id}")
    public String bookInfo(@PathVariable(value = "id") String id, Model model) {
        return bookRepository.findById(id).toString();
    }

}
