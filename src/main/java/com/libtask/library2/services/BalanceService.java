package com.libtask.library2.services;

import com.libtask.library2.entities.Book;
import com.libtask.library2.exceptions.BalanceConditionException;
import com.libtask.library2.repositories.BookRepository;
import com.libtask.library2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public void addBookToUserBalance(Long userId, Long bookId)
            throws BalanceConditionException, IllegalStateException {
        if (userRepository.existsById(userId)
                && bookRepository.existsById(bookId)) {
            Book bookInDb = bookRepository.findById(bookId)
                    .orElseThrow(() -> new IllegalArgumentException("Book with this ID is not exist"));

            if (bookInDb.getUserId() == null) {
                userRepository.addBookToUserBalance(userId, bookId);
            }
            else {
                throw new BalanceConditionException("Book is already taken");
            }
        }
        else throw new IllegalArgumentException("User with this ID is not exist");

    }

    public void removeBookFromUserBalance(Long userId, Long bookId)
            throws BalanceConditionException, IllegalArgumentException {
        if (userRepository.existsById(userId)) {
            Book bookInDb = bookRepository.findById(bookId)
                    .orElseThrow(() -> new IllegalArgumentException("Book with this ID is not exist"));

            if (bookInDb.getUserId() != null) {
                userRepository.removeBookFromUserBalance(userId, bookId);
            }
            else {
                throw new BalanceConditionException("Book is not on a user's balance");
            }
        }
        else throw new IllegalArgumentException("User with this ID is not exist");
    }
}
