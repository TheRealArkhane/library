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
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table (name = "books")
public class Book {
    static Random random = new Random();
    @Id
    String id = generateISBN();
    String name;
    String author;
    String genre;
    String userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book that = (Book) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static String generateISBN() {
        return  (100 + (int) (random() * 900))  + "-"
                + (int) (random() * 10) + "-"
                + (10 + (int) (random() * 90)) + "-"
                + (100000 + (int) (random() * 900000)) + "-"
                + (int) (random() * 10);
    }
}
