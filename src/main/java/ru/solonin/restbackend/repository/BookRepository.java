package ru.solonin.restbackend.repository;

import org.springframework.stereotype.Repository;
import ru.solonin.restbackend.domain.BookInfo;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private final List<BookInfo> BOOKS = new ArrayList<>();
    public BookInfo addBook(BookInfo bookInfo) {
        BOOKS.add(bookInfo);
        return bookInfo;
    }

    public List<BookInfo> booksList() {
        return BOOKS;
    }
}
