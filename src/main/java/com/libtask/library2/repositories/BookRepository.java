package com.libtask.library2.repositories;


import com.libtask.library2.entities.Book;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

    @NotNull Iterable<Book> findAll();
    Optional<Book> findByName(String name);
    Optional<Book> findByAuthorContains(String name);
    @NonNull Optional<Book> findById(@NonNull String id);
    Optional<Book> findAllByGenre(String genre);

}
