package ru.solonin.restbackend;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ru.solonin.restbackend.domain.BookInfo;

import static io.restassured.RestAssured.with;

public class BookControllerTest {

    static {
        RestAssured.baseURI = "http://localhost:8080";
    }

    private RequestSpecification spec =
            with()
                    .baseUri("http://localhost:8080")
                    .basePath("/");

    @Test
    void bankControllerTest() {
        BookInfo[] bookInfo = spec.get("book/getAll")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(BookInfo[].class);
    }
}
