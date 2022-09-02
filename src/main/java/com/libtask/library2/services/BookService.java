package com.libtask.library2.services;

import com.libtask.library2.dto.BookDto;
import com.libtask.library2.entities.Book;
import com.libtask.library2.repositories.BookRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    @NonNull
    private final BookRepository bookRepository;

    public BookDto bookToBookDto(Book book) {
        return new BookDto(
                book.getIsbn(),
                book.getName(),
                book.getAuthor(),
                book.getGenre());
    }

    public Page<Book> getCatalog(Pageable page) {
        return bookRepository.findAll(page);
    }
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.getBookByIsbn(isbn).orElseThrow();
    }

    public Page<Book> getFreeBooks(Pageable page) {
        return bookRepository.findFreeBooks(page);
    }

    public Page<Book> getTakenBooksByUserId(Long userId, Pageable page) {
        return bookRepository.findTakenBooksByUserId(userId, page);
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
}
