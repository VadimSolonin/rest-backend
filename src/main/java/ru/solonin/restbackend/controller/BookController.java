package ru.solonin.restbackend.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.solonin.restbackend.domain.BookInfo;
import ru.solonin.restbackend.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping("book/getAll")
    @ApiOperation("получение списка всех авторов")
    public List<BookInfo> getAllAuthorsInfo() {
        return bookService.booksList();
    }

    @PostMapping("book/addBook")
    @ApiOperation("добавление книги")
    public String addNewBook(@RequestBody BookInfo bookInfo) {
        bookService.addBook(bookInfo);
        return "Book successfully saved";
    }

    @GetMapping("book/find/{name}")
    @ApiOperation("поиск по названию книги")
    public BookInfo findBook(@PathVariable String name) {
        return bookService.findBookByName(name);
    }
}
