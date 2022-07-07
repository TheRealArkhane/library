package com.library.main;

import com.library.controllers.BookController;
import com.library.repositories.JdbcBookRepository;
import com.library.services.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = BookController.class)
@ComponentScan(basePackageClasses = BookService.class)
@ComponentScan(basePackageClasses = JdbcBookRepository.class)
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

}
