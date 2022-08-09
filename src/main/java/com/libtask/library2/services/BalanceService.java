package com.libtask.library2.services;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.User;
import com.libtask.library2.repositories.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    public void takeBook(User user, Book bookOnBalance) {
        balanceRepository.takeBook(user.getId(), bookOnBalance.getId());
    }

    public void returnBook(User user, Book bookToReturn) {
        balanceRepository.returnBook(user.getId(), bookToReturn.getId());
    }
}
