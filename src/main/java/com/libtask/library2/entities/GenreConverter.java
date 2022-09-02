package com.libtask.library2.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class GenreConverter implements AttributeConverter<Genre, String> {

    @Override
    public String convertToDatabaseColumn(Genre genre) {
        if (genre == null) {
            return null;
        }
        return genre.getName();
    }

    @Override
    public Genre convertToEntityAttribute(String genre) {
        if (genre == null) {
            return null;
        }

        return Stream.of(Genre.values())
                .filter(c -> c.getName().equals(genre))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
