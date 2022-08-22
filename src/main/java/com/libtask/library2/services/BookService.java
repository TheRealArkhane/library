package com.libtask.library2.services;

import com.libtask.library2.entities.Book;
import com.libtask.library2.entities.BookDto;
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
        return bookRepository.showAllBooks();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    public List<Book> getBooksOnBalance() {
        return bookRepository.getBooksOnBalance();
    }

    public List<Book> getTakenBooksListByUserId(Long userId) {
        return bookRepository.getTakenBooksListByUserId(userId);
    }

    public Book addBook(BookDto bookDto) {
        Book newBook = new Book(
                bookDto.getIsbn(),
                bookDto.getName(),
                bookDto.getAuthor(),
                bookDto.getGenre());
        bookRepository.save(newBook);
        return newBook;
    }

    public void deleteBook(Book book) {
        if (book.getUserId() != null) {
            throw new RuntimeException("Невозможно удалить взятую книгу");
        }
        bookRepository.delete(book);
    }

    public static List<Book> sortByAuthor(List<Book> bookList, String author) {
        return bookList.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
        // instead of .equalsIgnoreCase(...) can there be .contains(CharSequence ...)?
    }

    public List<Book> sortByName(List<Book> bookList, String name) {
        return bookList.stream()
                .filter(book -> book.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
        // instead of .equalsIgnoreCase(...) can there be .contains(CharSequence ...)?
    }

    public List<Book> sortByGenre(List<Book> bookList, Genre genre) {
        return bookList.stream()
                .filter(book -> book.getGenre().equals(genre))
                .collect(Collectors.toList());
        // instead of .equalsIgnoreCase(...) can there be .contains(CharSequence ...)?
    }

    //собственное решение
    public List<List<Book>> pagination(List<Book> bookList, int pageValue) {
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
    public List<List<Book>> newPagination(List<Book> bookList, int pageValue) {
        List<List<Book>> pagesList = new ArrayList<>();
        for (int i = 0; i < bookList.size(); i += pageValue) {
            pagesList.add(bookList.subList(i, Math.min(i + pageValue, bookList.size())));
        }
        return pagesList;
    }
}
