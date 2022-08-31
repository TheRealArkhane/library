package com.libtask.library2.services;

import com.libtask.library2.entities.Book;
import com.libtask.library2.dto.BookDto;
import com.libtask.library2.entities.Genre;
import com.libtask.library2.repositories.BookRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    @NonNull
    private final BookRepository bookRepository;
    public List<Book> getCatalog() {
        return bookRepository.getAllBooks();
    }

    public BookDto bookToBookDto(Book book) {
        return new BookDto(
                book.getIsbn(),
                book.getName(),
                book.getAuthor(),
                book.getGenre());
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.getBookByIsbn(isbn).orElseThrow();
    }

    public List<Book> getFreeBooks() {
        return bookRepository.findFreeBooks();
    }

    public List<Book> getTakenBooksByUserId(Long userId) {
        return bookRepository.findTakenBooksByUserId(userId);
    }

    public Book addBook(BookDto bookDto) {
        Book newBook = new Book(
                bookDto.getIsbn(),
                bookDto.getName(),
                bookDto.getAuthor(),
                bookDto.getGenre());
        bookRepository.save(newBook);
        return newBook;
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);
        if (book.getUserId() != null) {
            throw new RuntimeException("Can't delete book that already taken");
        }
        bookRepository.delete(book);
    }

    public List<Book> getCatalogSortedByName() {
        return bookRepository.getCatalogSortedByName();
    }

    public List<Book> getCatalogSortedByAuthor() {
        return bookRepository.getCatalogSortedByAuthor();
    }

    public List<Book> getCatalogSortedByGenre() {
        return bookRepository.getCatalogSortedByGenre();
    }

}
