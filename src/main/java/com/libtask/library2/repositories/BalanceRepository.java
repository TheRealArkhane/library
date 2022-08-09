package com.libtask.library2.repositories;

import com.libtask.library2.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BalanceRepository extends JpaRepository <Book, String> {

    @Modifying
    @Query(value = "UPDATE books " +
            "SET user_id = :user_id " +
            "WHERE books.id = :book_id " +
            "AND books.user_id IS NULL",
            nativeQuery = true)
    void takeBook (@Param("user_id") String userId, @Param("book_id") String bookId);

    @Modifying
    @Query(value = "UPDATE books SET user_id = NULL " +
            "WHERE books.user_id = :user_id " +
            "AND books.id = :book_id",
            nativeQuery = true)
    void returnBook (@Param("user_id") String userId, @Param("book_id") String bookId);

}
