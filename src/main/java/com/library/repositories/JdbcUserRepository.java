package com.library.repositories;

import com.library.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<User> findAll() {
        return jdbc.query("select * from users", this::mapRowToUser);
    }

    @Override
    public User findOne(String id) {
        return jdbc.queryForObject(
                "select * from users where id=?",
                this::mapRowToUser, id);
    }

    @Override
    public User save(User user) {
        return null;
    }

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getString("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"));
    }
}
