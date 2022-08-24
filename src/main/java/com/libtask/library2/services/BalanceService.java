package com.libtask.library2.services;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.User;
import com.libtask.library2.repositories.BookRepository;
import com.libtask.library2.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

    @NonNull
    private UserRepository userRepository;
    @NonNull
    private BookRepository bookRepository;

    public void addBookToUserBalance(User user, Book bookToTake) {
        if (!userRepository.existsById(user.getId())
                || !bookRepository.existsById(bookToTake.getId())
                || bookRepository.findById(bookToTake.getId()).get().getUserId() != null) {
            throw new IllegalStateException("не соблюдены условия выполнения");
        }
        userRepository.addBookToUserBalance(user.getId(), bookToTake.getId());
    }

    public void removeBookFromUserBalance(User user, Book bookToReturn) {
        if (!userRepository.existsById(user.getId())
                || !bookRepository.existsById(bookToReturn.getId())
                || bookRepository.findById(bookToReturn.getId()).get().getUserId() == null) {
            throw new IllegalStateException("не соблюдены условия выполнения");
        }
        userRepository.removeBookFromUserBalance(user.getId(), bookToReturn.getId());
    }
}
