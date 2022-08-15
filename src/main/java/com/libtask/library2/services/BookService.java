package com.libtask.library2.services;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.Genre;
import com.libtask.library2.entities.User;
import com.libtask.library2.repositories.BookRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    @NonNull
    private final BookRepository bookRepository;
    public List<Book> showCatalog() {
        return bookRepository.showCatalog();
    }

    public Book getBookById(int id) {
        return bookRepository.findById(String.valueOf(id)).get();
    }

    public List<Book> getBooksOnBalance() {
        return bookRepository.getBooksOnBalance();
    }

    @OneToMany
    public List<Book> getTakenBooksListByUser(User user) {
        return bookRepository.getTakenBooksListByUserId(user.getId());
    }

    public void addBook(String isbn, String name, String author, Genre genre) {
        Book book = new Book(isbn, name, author, genre);
        bookRepository.save(book);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    public List<Book> sortByAuthor(List<Book> bookList, String author) {
        return bookList.stream().filter(book -> book.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
        // instead of .equalsIgnoreCase(...) there can be .contains(...)
    }

    public List<Book> sortByName(List<Book> bookList, String name) {
        return bookList.stream().filter(book -> book.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        // instead of .equalsIgnoreCase(...) there can be .contains(...)
    }

    public List<Book> sortByGenre(List<Book> bookList, Genre genre) {
        return bookList.stream().filter(book -> book.getGenre().equals(genre)).collect(Collectors.toList());
    }

    //собственное решение
    public List<List<Book>> partitionToPages(List<Book> bookList, int pageValue) {
        List<List<Book>> pagesList = new ArrayList<>();
        int size = bookList.size();
        for (int i = 0; i <= bookList.size() / pageValue; i++) {
            if (size >= pageValue) {
                List<Book> pageList = bookList.subList(i * pageValue,(i * pageValue) + pageValue - 1);
                pagesList.add(pageList);
                size -= pageValue;
            }
            else {
                List<Book> pageList = bookList.subList(i * pageValue, bookList.size() - 1);
                pagesList.add(pageList);
            }
        }
        return pagesList;
    }

    //найденное решение
    public List<List<Book>> newPartitionToPages(List<Book> bookList, int pageValue) {
        List<List<Book>> pagesList = new ArrayList<>();
        for (int i = 0; i < bookList.size(); i += pageValue) {
            pagesList.add(bookList.subList(i, Math.min(i + pageValue, bookList.size())));
        }
        return pagesList;
    }
}
