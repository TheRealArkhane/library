package com.library.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Book {

    private final Integer bookId;
    private final String bookName;
    private final Genre genre;

    public enum Genre {
        DRAMA,
        DETECTIVE,
        COMEDY,
        ADVENTURE,
        EDUCATION,
        ROMANCE,
        OTHER
    }
}
