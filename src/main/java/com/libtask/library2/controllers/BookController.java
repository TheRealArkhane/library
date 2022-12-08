package com.libtask.library2.controllers;

import com.libtask.library2.dto.BookDto;
import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.Genre;
import com.libtask.library2.exceptions.BalanceConditionException;
import com.libtask.library2.repositories.BookRepository;
import com.libtask.library2.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
@CrossOrigin
public class BookController {
    private final BookService bookService;
    private final BookRepository bookRepository;

    @PostMapping("/add")
    public Book addBookPost(@RequestBody BookDto bookDto) {
       return bookService.addBook(bookDto);
    }

    @GetMapping("/all")
    public Page<Book> getCatalog(Pageable page) {
        return bookRepository.findAll(page);
    }

    @GetMapping("/free")
    public Page<Book> getFreeBooks(@PageableDefault(sort = "id") Pageable page) {
        return bookRepository.findFreeBooks(page);
    }

    @GetMapping("/{id}")
    public BookDto getBookInfo(@PathVariable(value = "id") Long id) throws IllegalArgumentException {
        return new BookDto(bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book with this ID is not exist")));
    }

    @PutMapping("/{id}")
    public Book updateBookInfo(
            @PathVariable(value = "id") Long id,
            @RequestBody BookDto bookDto) throws IllegalArgumentException {
        bookRepository.updateBookInfo(
                bookDto.getIsbn(),
                bookDto.getName(),
                bookDto.getAuthor(),
                bookDto.getGenre().getName(),
                id);
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book with this ID is not exist"));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) throws IllegalArgumentException, BalanceConditionException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book with this ID is not exist"));
        if (book.getUserId() == null) {
            bookRepository.delete(book);
        }
        else throw new BalanceConditionException("Can't delete book that already taken");
    }
}
