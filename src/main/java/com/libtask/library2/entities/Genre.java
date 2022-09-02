package com.libtask.library2.entities;

public enum Genre {
    DRAMA("Драма"),
    DETECTIVE("Детектив"),
    COMEDY("Комедия"),
    ADVENTURE("Приключения"),
    EDUCATION("Образование"),
    ROMANCE("Романтика"),
    OTHER("Другое");

    private final String name;
    Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }
 }