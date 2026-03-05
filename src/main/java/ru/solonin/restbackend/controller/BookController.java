package ru.solonin.restbackend.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
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
    public ResponseEntity<CreateBookResponse> addNewBook(@RequestBody BookInfo bookInfo) {
        BookInfo savedBook = bookService.addBook(bookInfo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateBookResponse(savedBook, "Book successfully saved"));
    }

    @GetMapping("/search")
    @ApiOperation("поиск по названию книги")
    public BookInfo findBook(@RequestParam(name = "bookName") String bookName) {
        BookInfo foundBook = bookService.findBookByName(bookName);
        if (foundBook == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        return foundBook;
    }

    @Getter
    public static class CreateBookResponse {
        private final BookInfo book;
        private final String message;

        public CreateBookResponse(BookInfo book, String message) {
            this.book = book;
            this.message = message;
        }
    }
}
