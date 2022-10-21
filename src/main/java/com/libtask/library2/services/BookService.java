package com.libtask.library2.services;

import com.libtask.library2.dto.BookDto;
import com.libtask.library2.entities.Book;
import com.libtask.library2.repositories.BookRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    @NonNull
    private final BookRepository bookRepository;

    public Book getBookByIsbn(String isbn) throws IllegalArgumentException {
        return bookRepository.getBookByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book with this ISBN is not exist"));
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

    public Page<Book> findBooksSortedByCriterion(String criterion, Pageable page) {
        Pageable paging = PageRequest.of(
                page.getPageNumber(),
                page.getPageSize(),
                Sort.by(criterion));
        return bookRepository.findAll(paging);
    }
}
