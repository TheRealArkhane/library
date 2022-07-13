package com.library.factories;

import com.library.dto.BookDto;
import com.library.entities.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookDtoFactory {

    public static BookDto makeBookDto(BookEntity bookEntity) {
        return BookDto.builder()
                .id(bookEntity.getId())
                .bookName(bookEntity.getBookName())
                .author(bookEntity.getAuthor())
                .genre(bookEntity.getGenre())
                .userId(bookEntity.getUserId())
                .build();
    }
}
