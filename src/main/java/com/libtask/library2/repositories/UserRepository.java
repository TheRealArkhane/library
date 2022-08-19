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
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM app_user", nativeQuery = true)
    List<User> showAllUsers();

    @Modifying
    @Query(value = "UPDATE app_user SET email = :newEmail WHERE app_user.id = :user_id", nativeQuery = true)
    void changeEmail(@Param("newEmail") String email, @Param("user_id") int userId);

    @Modifying
    @Query(value = "UPDATE book " +
            "SET user_id = :user_id " +
            "WHERE book.id = :book_id " +
            "AND book.user_id IS NULL",
            nativeQuery = true)
    void takeBook (@Param("user_id") int userId, @Param("book_id") int bookId);

    @Modifying
    @Query(value = "UPDATE book SET user_id = NULL " +
            "WHERE book.user_id = :user_id " +
            "AND book.id = :book_id",
            nativeQuery = true)
    void returnBook (@Param("user_id") int userId, @Param("book_id") int bookId);
}
