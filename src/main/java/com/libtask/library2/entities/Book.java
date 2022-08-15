package com.libtask.library2.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table (name = "books")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    int id;
    @NonNull
    @Column(name = "isbn")
    String isbn;
    @NonNull
    @Column(name = "name")
    String name;
    @NonNull
    @Column (name = "author")
    String author;
    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    Genre genre;
    @Column(name = "user_id")
    String userId;
}

