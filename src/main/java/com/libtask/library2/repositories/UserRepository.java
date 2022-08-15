package com.libtask.library2.repositories;

import com.libtask.library2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT first_name,last_name FROM users", nativeQuery = true)
    List<User> showAllUsers();

    @Modifying
    @Query(value = "UPDATE users SET email = :newEmail WHERE users.id = :user_id", nativeQuery = true)
    void changeEmail(@Param("newEmail") String email, @Param("user_id") int userId);

    @Modifying
    @Query(value = "UPDATE books " +
            "SET user_id = :user_id " +
            "WHERE books.id = :book_id " +
            "AND books.user_id IS NULL",
            nativeQuery = true)
    void takeBook (@Param("user_id") int userId, @Param("book_id") int bookId);

    @Modifying
    @Query(value = "UPDATE books SET user_id = NULL " +
            "WHERE books.user_id = :user_id " +
            "AND books.id = :book_id",
            nativeQuery = true)
    void returnBook (@Param("user_id") int userId, @Param("book_id") int bookId);
}
