package ru.solonin.restbackend.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.solonin.restbackend.domain.BookInfo;
import ru.solonin.restbackend.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping
    @ApiOperation("получение списка всех книг")
    public List<BookInfo> getAllBooks() {
        return bookService.booksList();
    }

    @PostMapping
    @ApiOperation("добавление книги")
    public String addNewBook(@RequestBody BookInfo bookInfo) {
        bookService.addBook(bookInfo);
        return "Book successfully saved";
    }

    @GetMapping("/search")
    @ApiOperation("поиск по названию книги")
    public BookInfo findBook(@RequestParam(name = "name") String name) {
        return bookService.findBookByName(name);
    }
}
