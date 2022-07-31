package com.libtask.library2.repositories;


import com.libtask.library2.entities.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @NotNull Iterable<User> findAll();
    User findByEmail (String email);
    Optional<User> findByLastName (String lastName);
}
