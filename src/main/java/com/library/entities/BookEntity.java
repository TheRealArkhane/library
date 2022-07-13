package com.library.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    String id;
    String bookName;
    String author;
    String genre;
    String userId;

}
