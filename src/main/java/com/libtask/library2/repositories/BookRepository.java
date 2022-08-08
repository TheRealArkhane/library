package com.libtask.library2.repositories;

import com.libtask.library2.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    @Query(value = "SELECT name,author,genre FROM books", nativeQuery = true)
    List<Book> showCatalog();
    @Query(value = "SELECT name,author,genre FROM books WHERE user_id IS NULL", nativeQuery = true)
    List<Book> getBooksOnBalance();
    @Query(value = "SELECT name,author,genre FROM books WHERE user_id = :user_id", nativeQuery = true)
    List<Book> getTakenBooksListByUserId(@Param("user_id") String userId);
}
