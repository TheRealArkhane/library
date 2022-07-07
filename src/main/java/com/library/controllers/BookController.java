package com.library.controllers;

import com.library.entity.Book;
import com.library.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{bookId}")
   public Book getBook(@PathVariable String bookId) {

        return bookService.getBook(Integer.parseInt(bookId));
    }
}
