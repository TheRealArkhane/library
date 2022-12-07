package com.libtask.library2.repositories;

import com.libtask.library2.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    @Query(value = "SELECT * FROM book WHERE user_id IS NULL", nativeQuery = true)
    Page<Book> findFreeBooks(Pageable page);

    @Query(value = "SELECT * FROM book WHERE user_id = :user_id", nativeQuery = true)
    Page<Book> findTakenBooksByUserId(@Param("user_id") Long userId, Pageable page);

    @Modifying
    @Transactional
    @Query(value = "UPDATE book SET isbn = :isbn, name = :name, author = :author, genre  = :genre WHERE id = :id", nativeQuery = true)
    void updateBookInfo(String isbn, String name, String author, String genre, @Param("id") Long id);
}
