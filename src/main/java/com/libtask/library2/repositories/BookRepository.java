package com.libtask.library2.repositories;

import com.libtask.library2.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Optional<Book> getBookByIsbn(String isbn);

    @Query(value = "SELECT * FROM book WHERE user_id IS NULL", nativeQuery = true)
    Page<Book> findFreeBooks(Pageable page);

    @Query(value = "SELECT * FROM book WHERE user_id = ?1", nativeQuery = true)
    Page<Book> findTakenBooksByUserId(Long userId, Pageable page);
}
