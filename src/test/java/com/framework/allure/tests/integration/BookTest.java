package com.framework.allure.tests.integration;

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

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Execution(ExecutionMode.CONCURRENT)
public class BookTest extends TestCore {

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

        step("WHEN delete the previously created user", () -> {
            ResponseEntity<Void> response = getUserService().deleteUser(contextUser.get().getId());
            assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NO_CONTENT)).isTrue();
        });

        final AtomicReference<BookDTO> contextBook = new AtomicReference<>();
        step("THEN creating a book for the user is prohibited", () -> {
            contextBook.set(getBookService().initContextBook(contextUser.get().getId()));
            final ResponseEntity<BookDTO> response = getBookService().registerBook(contextBook.get());
            assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isTrue();
        });
    }
}
