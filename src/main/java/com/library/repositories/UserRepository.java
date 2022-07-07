package com.library.repositories;

import com.library.entity.User;

public interface UserRepository {

    Iterable<User> findAll();
    User findOne(String id);
    User save(User user);
}
