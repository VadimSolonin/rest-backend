package ru.solonin.restbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get all books", description = "Returns the full list of books currently stored in the service")
    public List<BookInfo> getAllBooks() {
        return bookService.booksList();
    }

    @PostMapping
    @Operation(summary = "Create a book", description = "Adds a new book record and returns the saved payload with confirmation message")
    public ResponseEntity<CreateBookResponse> addNewBook(@RequestBody BookInfo bookInfo) {
        BookInfo savedBook = bookService.addBook(bookInfo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CreateBookResponse(savedBook, "Book successfully saved"));
    }

    @GetMapping("/search")
    @Operation(summary = "Search book by name", description = "Looks up a book by its exact title and returns 404 if nothing was found")
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
