package ru.solonin.restbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.solonin.restbackend.domain.BookInfo;
import ru.solonin.restbackend.repository.BookRepository;
import ru.solonin.restbackend.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Override
    public List<BookInfo> booksList() {
        return repository.booksList();
    }

    @Override
    public BookInfo addBook(BookInfo bookInfo) {
        return repository.addBook(bookInfo);
    }
}
