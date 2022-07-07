package com.library.services;

import com.library.entity.Book;
import com.library.repositories.JdbcBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private JdbcBookRepository bookRepository;

    public Book getBook(int bookId) {
        return bookRepository.getBookForId(bookId);
    }
}
