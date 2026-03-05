## Book Lab

A Spring-powered platform for experimenting with controllers, services, repositories, validation, and swagger documentation.
### Architecture overview

```
spring-book-lab
├── RestBackendApplication      # application entry point
├── controller/BookController   # REST endpoints
├── service/BookService(+impl)  # business logic layer
├── repository/BookRepository   # in-memory persistence
├── domain/BookInfo             # DTO
└── exception/
    ├── ApiExceptionHandler     # global error handler
    └── Book*Exception          # domain-specific validation errors
```

### Running the application

| Platform | Command |
|---|---|
| macOS / Linux | `./gradlew bootRun` |
| Windows | `.\gradlew.bat bootRun` |

The service starts on `http://localhost:8080`.

### Tests

API tests rely on RestAssured and send HTTP requests to the running server. Start the app first, then run:

| Platform | Command |
|---|---|
| macOS / Linux | `./gradlew test` |
| Windows | `.\gradlew.bat test` |

