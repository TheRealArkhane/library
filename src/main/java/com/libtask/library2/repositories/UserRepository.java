package com.libtask.library2.repositories;

import com.libtask.library2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT first_name,last_name FROM users", nativeQuery = true)
    List<User> showAllUsers();

    @Modifying
    @Query(value = "UPDATE users SET email = :newEmail WHERE users.id = :user_id", nativeQuery = true)
    void changeEmail(@Param("newEmail") String email, @Param("user_id") String userId);
}
