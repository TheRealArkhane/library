package com.library.repositories;

import com.library.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcBookRepository implements BookRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcBookRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Book> findAll() {
        return jdbc.query("select * from books", this::mapRowToBook);
    }

    @Override
    public Book findOne(String id) {
        return jdbc.queryForObject(
                "select * from books where id=?",
                this::mapRowToBook, id);
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        return new Book(rs.getString("id"),
                        rs.getString("name"),
                        Book.Genre.valueOf(rs.getString("genre")));
    }
}
