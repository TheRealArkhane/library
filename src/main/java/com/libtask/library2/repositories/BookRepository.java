package com.libtask.library2.repositories;

import com.libtask.library2.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book getBookByIsbn(String isbn);
    @Query(value = "SELECT * FROM book", nativeQuery = true)
    List<Book> showAllBooks();

    @Query(value = "SELECT * FROM book WHERE user_id IS NULL", nativeQuery = true)
    List<Book> getBooksInStock();

    @Query(value = "SELECT * FROM book WHERE user_id = :user_id", nativeQuery = true)
    List<Book> getTakenBooksListByUserId(@Param("user_id") Long userId);

}
