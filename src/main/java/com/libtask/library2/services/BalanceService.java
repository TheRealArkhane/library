package com.libtask.library2.services;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.User;
import com.libtask.library2.repositories.BookRepository;
import com.libtask.library2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public void addBookToUserBalance(User user, Book bookToTake) {
        User userInDb = userRepository.findById(user.getId()).orElseThrow();
        Book bookInDb = bookRepository.findById(bookToTake.getId()).orElseThrow();
        if (bookInDb.getUserId() != null) {
            throw new IllegalStateException("Book is already taken");
        }
        else userRepository.addBookToUserBalance(userInDb.getId(), bookInDb.getId());
    }

    public void removeBookFromUserBalance(User user, Book bookToReturn) {
        User userInDb = userRepository.findById(user.getId()).orElseThrow();
        Book bookInDb = bookRepository.findById(bookToReturn.getId()).orElseThrow();
        if (!bookInDb.getUserId().equals(userInDb.getId())) {
            throw new IllegalStateException("Book is not on a user's balance");
        }
        else userRepository.removeBookFromUserBalance(userInDb.getId(), bookInDb.getId());
    }
}
