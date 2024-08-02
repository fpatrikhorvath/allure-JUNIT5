package com.framework.allure.tests.crud;

import com.framework.allure.core.TestCore;
import com.framework.allure.rest.response.BookDTO;
import com.framework.allure.rest.response.CreateUser201ResponseDTO;
import com.framework.allure.rest.response.UserDTO;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class BookCRUDTest extends TestCore {

    @Test
    @Description("Create book for user")
    public void createBookForUser() {
        final AtomicReference<UserDTO> contextUser = new AtomicReference<>();

        step("GIVEN a new user of status A", () -> {
            contextUser.set(getUserService().initContextUser("A"));
            final ResponseEntity<CreateUser201ResponseDTO> response = getUserService().registerUser(contextUser.get());
            assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.CREATED)).isTrue();
            contextUser.get().setId(Objects.requireNonNull(response.getBody()).getId());
        });

        final AtomicReference<BookDTO> contextBook = new AtomicReference<>();

        step("WHEN create a new book for the user", () -> {
            contextBook.set(getBookService().initContextBook(contextUser.get().getId()));
            final ResponseEntity<BookDTO> response = getBookService().registerBook(contextBook.get());
            assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.CREATED)).isTrue();
            contextBook.get().setId(Objects.requireNonNull(response.getBody()).getId());
        });

        step("THEN verify that book exist", () -> {
            final ResponseEntity<List<BookDTO>> response = getBookService().getBooks(contextBook.get());
            assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isTrue();

            final BookDTO actBook = Objects.requireNonNull(response.getBody())
                    .stream()
                    .filter(b -> Objects.equals(b.getTitle(), contextBook.get().getTitle()))
                    .findFirst()
                    .orElse(null);

            assert actBook != null;
            assertThat(actBook.getTitle()).isEqualTo(contextBook.get().getTitle());
            assertThat(actBook.getAuthor()).isEqualTo(contextBook.get().getAuthor());
            assertThat(actBook.getUserId()).isEqualTo(contextUser.get().getId());
        });
    }

    @Test
    @Description("Create book for deleted user")
    public void createBookForDeletedUser() {
        final AtomicReference<UserDTO> contextUser = new AtomicReference<>();

        step("GIVEN a new user of status A", () -> {
            contextUser.set(getUserService().initContextUser("A"));
            final ResponseEntity<CreateUser201ResponseDTO> response = getUserService().registerUser(contextUser.get());
            assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.CREATED)).isTrue();
            contextUser.get().setId(Objects.requireNonNull(response.getBody()).getId());
        });

        final AtomicReference<BookDTO> contextBook = new AtomicReference<>();

        step("GIVEN create a new book for the user", () -> {
            contextBook.set(getBookService().initContextBook(contextUser.get().getId()));
            final ResponseEntity<BookDTO> response = getBookService().registerBook(contextBook.get());
            assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.CREATED)).isTrue();
            contextBook.get().setId(Objects.requireNonNull(response.getBody()).getId());
        });

        step("WHEN delete the previously created book", () -> {
            final ResponseEntity<Void> response = getBookService().deleteBook(contextUser.get(), contextBook.get());
            assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NO_CONTENT)).isTrue();
        });

        step("THEN verify that book does not exist", () -> {
            final ResponseEntity<List<BookDTO>> response = getBookService().getBooks(contextBook.get());
            assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isTrue();

            final BookDTO actBook = Objects.requireNonNull(response.getBody())
                    .stream()
                    .filter(b -> Objects.equals(b.getTitle(), contextBook.get().getTitle()))
                    .findFirst()
                    .orElse(null);

            assertThat(actBook).isNull();
        });
    }
}
