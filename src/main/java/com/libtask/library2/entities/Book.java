package com.libtask.library2.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Random;

import static java.lang.Math.random;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table (name = "books")
public class Book {
    static Random random = new Random();
    @Id
    @Column(name = "id")
    String id;
    @Column(name = "name")
    String name;
    @Column (name = "author")
    String author;
    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    Genre genre;
    @Column(name = "user_id")
    String userId;
}

