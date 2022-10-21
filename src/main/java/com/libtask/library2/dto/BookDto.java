package com.libtask.library2.dto;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.Genre;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class BookDto {
    @NonNull
    private final String isbn;
    @NonNull
    private final String name;
    @NonNull
    private final String author;
    @NonNull
    @Enumerated(EnumType.STRING)
    private final Genre genre;

    public BookDto(@NotNull Book book) {
        this.isbn = book.getIsbn();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.genre = book.getGenre();
    }

}
