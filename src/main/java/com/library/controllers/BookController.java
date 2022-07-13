package com.library.controllers;

import com.library.dto.BookDto;
import com.library.entities.BookEntity;
import com.library.entities.Genre;
import com.library.factories.BookDtoFactory;
import com.library.repositories.BookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class BookController {

    private final BookDtoFactory bookDtoFactory;
    private final BookRepository bookRepository;

    public static final String SHOW_BOOK = "api/books/show";

    @GetMapping(SHOW_BOOK)
    public BookDto createBook(@RequestParam String id, String bookName, String author, Genre genre) {
        return BookDtoFactory.makeBookDto(new BookEntity("0230124129",
                "Kolyas: Origins",
                "Kolyas",
                "Комедия",
                null));
    }
}
