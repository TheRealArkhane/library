package com.library.repositories;

import com.library.entity.Book;
import com.library.entity.Book.Genre;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class JdbcBookRepository {

     private final Map<Integer, Book> catalog = new HashMap<>(Map.of(0, new Book(0,"Ельня. История в цветах", Genre.EDUCATION)));

     public Book getBookForId(int bookId) {
         return catalog.get(bookId);
     }
}
