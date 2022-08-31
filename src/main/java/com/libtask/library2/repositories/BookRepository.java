package com.libtask.library2.repositories;

import com.libtask.library2.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> getBookByIsbn(String isbn);
    @Query(value = "SELECT * FROM book ORDER BY name", nativeQuery = true)
    List<Book> getCatalogSortedByName();

    @Query(value = "SELECT * FROM book ORDER BY author", nativeQuery = true)
    List<Book> getCatalogSortedByAuthor();

    @Query(value = "SELECT * FROM book ORDER BY genre", nativeQuery = true)
    List<Book> getCatalogSortedByGenre();

    @Query(value = "SELECT * FROM book", nativeQuery = true)
    List<Book> getAllBooks();

    @Query(value = "SELECT * FROM book WHERE user_id IS NULL", nativeQuery = true)
    List<Book> findFreeBooks();

    @Query(value = "SELECT * FROM book WHERE user_id = ?1", nativeQuery = true)
    List<Book> findTakenBooksByUserId(Long userId);

}
