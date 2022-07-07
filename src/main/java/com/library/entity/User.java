package com.library.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@RequiredArgsConstructor
public class User {

    private final String id;
    @NotNull (message = "поле не может быть пустым")
    @Size(min = 2, message = "Название должно содержать по меньшей мере 2 знака")
    private final String firstName;
    @NotNull (message = "поле не может быть пустым")
    @Size(min = 2, message = "Название должно содержать по меньшей мере 2 знака")
    private final String lastName;
    @NotNull (message = "поле не может быть пустым")
    private final String email;
}
