package com.libtask.library2.dto;

import com.libtask.library2.entities.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {

    @NonNull
    final String isbn;
    @NonNull
    final String name;
    @NonNull
    final String author;
    @NonNull
    @Enumerated(EnumType.STRING)
    final Genre genre;
}
