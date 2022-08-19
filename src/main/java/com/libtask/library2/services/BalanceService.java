package com.libtask.library2.services;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.User;
import com.libtask.library2.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

    @NonNull
    private UserRepository userRepository;

    public User takeBook(User user, Book bookOnBalance) {
        userRepository.takeBook(user.getId(), bookOnBalance.getId());
        return user;
    }

    public User returnBook(User user, Book bookToReturn) {
        userRepository.returnBook(user.getId(), bookToReturn.getId());
        return user;
    }
}
