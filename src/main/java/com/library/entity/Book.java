package com.library.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@RequiredArgsConstructor
public class Book {

    private final String id;
    @NotNull
    @Size(min = 2, message = "Название должно содержать по меньшей мере 2 знака")
    private final String name;
    @NotNull
    @Size(min = 1, message = "У книги должен быть по меньшей мере 1 жанр")
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
