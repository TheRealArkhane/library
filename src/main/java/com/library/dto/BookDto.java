package com.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {
    @NonNull
    String id;
    @JsonProperty("name")
    @NonNull
    String bookName;
    @NonNull
    String author;
    @NonNull
    String genre;
    String userId;
}
