package com.library.repositories;

import com.library.entity.Book;

public interface BookRepository {

    Iterable<Book> findAll();
    Book findOne(String id);
    Book save(Book book);
}
