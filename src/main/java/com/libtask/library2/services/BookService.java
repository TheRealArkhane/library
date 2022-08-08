package com.libtask.library2.services;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.Genre;
import com.libtask.library2.entities.User;
import com.libtask.library2.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> showCatalog() {
        return bookRepository.showCatalog();
    }

    public Book getBook(String id) {
        return bookRepository.findById(id).get();
    }

    public List<Book> getBooksOnBalance() {
        return bookRepository.getBooksOnBalance();
    }

    public List<Book> getTakenBooksListByUser(User user) {
        return bookRepository.getTakenBooksListByUserId(user.getId());
    }

    public void addBook(String id, String name, String author, Genre genre) {
        Book book = new Book(id, name, author, genre, null);
        bookRepository.save(book);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}
