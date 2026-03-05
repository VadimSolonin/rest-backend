package ru.solonin.restbackend;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import ru.solonin.restbackend.domain.BookInfo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class BookControllerTest {

    static {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    void addNewBook_returns201AndMessage_andThenSearchFindsIt() {
        BookInfo testBook = BookInfo.builder()
                .author("VS")
                .bookName("About me")
                .publicationDate("2026-05-03")
                .build();

        int initialSize =
                given()
                .when()
                        .get("/api/v1/books")
                .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getList("$")
                        .size();

        given()
                .contentType(ContentType.JSON)
                .body(testBook)
        .when()
                .post("/api/v1/books")
        .then()
                .statusCode(201)
                .body("message", equalTo("Book successfully saved"))
                .body("book.author", equalTo("VS"))
                .body("book.bookName", equalTo("About me"))
                .body("book.publicationDate", equalTo("2026-05-03"));

        given()
        .when()
                .get("/api/v1/books")
        .then()
                .statusCode(200)
                .body("$", hasSize(initialSize + 1));

        given()
                .queryParam("bookName", "About me")
        .when()
                .get("/api/v1/books/search")
        .then()
                .statusCode(200)
                .body("author", equalTo("VS"))
                .body("bookName", equalTo("About me"))
                .body("publicationDate", equalTo("2026-05-03"));
    }

    @Test
    void searchBook_whenNotFound_returns404WithApiErrorBody() {
        given()
                .queryParam("bookName", "missing")
        .when()
                .get("/api/v1/books/search")
        .then()
                .statusCode(404)
                .body("code", equalTo("404"))
                .body("message", equalTo("Book not found"));
    }
}
