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
@Table (name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE)
    int id;
    @Column(name = "first_name")
    @NonNull
    String firstName;
    @Column(name = "last_name")
    @NonNull
    String lastName;
    @NonNull
    @Column(name = "email")
    String email;
}
