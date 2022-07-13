package com.library.repositories;

import com.library.entities.BookEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {

    Optional<BookEntity> findByBookName(String name);
    Optional<BookEntity> findByAuthorContains(String name);
    @NonNull Optional<BookEntity> findById(@NonNull String id);
    Optional<BookEntity> findAllByGenre(String genre);

}
