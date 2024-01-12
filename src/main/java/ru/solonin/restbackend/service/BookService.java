package ru.solonin.restbackend.service;

import ru.solonin.restbackend.domain.BookInfo;

import java.util.List;


public interface BookService {
    public List<BookInfo> booksList();
    public BookInfo addBook(BookInfo bookInfo);
    public BookInfo findBookByName(String bookName);
}
