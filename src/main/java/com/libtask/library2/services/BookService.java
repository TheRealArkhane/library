package com.libtask.library2.services;


import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.User;
import com.libtask.library2.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void takeBook(User user, Book bookOnBalance) {
       Book bookToGet = bookRepository.findById(bookOnBalance.getId()).orElseThrow();
       if (bookToGet.getUserId() == null) {
           bookToGet.setUserId(user.getId());
       }
       bookRepository.save(bookToGet);
    }

    public void returnBook(User user, Book bookToReturn) {
        Book bookOnBalance = bookRepository.findById(bookToReturn.getId()).orElseThrow();
        if (bookOnBalance.getUserId().equals(user.getId())
                && bookOnBalance.getUserId() != null) {
            bookOnBalance.setUserId(null);
        }
        bookRepository.save(bookOnBalance);
    }

    public List<Book> getBooksOnBalance() {
        return bookRepository.findAll()
                .stream()
                .filter(book -> book.getUserId() == null)
                .collect(Collectors.toList());
    }

    public List<Book> getTakenBooksListByUser(User user) {
        return bookRepository.findAll()
                .stream()
                .filter(book -> book.getUserId()
                        .equals(user.getId()))
                .collect(Collectors.toList());
    }
}
