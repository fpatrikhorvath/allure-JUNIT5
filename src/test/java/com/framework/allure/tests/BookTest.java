package com.framework.allure.tests;

import com.framework.allure.core.TestCore;
import com.framework.allure.rest.response.CreateUser201ResponseDTO;
import com.framework.allure.rest.response.*;
import io.qameta.allure.Description;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.SAME_THREAD)
public class BookTest extends TestCore {

    @Test
    @Description("Create book for user")
    public void createBookForUser() {
        final AtomicReference<UserDTO> user = new AtomicReference<>();

        step("GIVEN a new user of status A", () -> {
            user.set(getUserService().initContextUser("A"));
            final ResponseEntity<CreateUser201ResponseDTO> response = getUserService().registerUser(user.get());
            assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.CREATED));
        });
    }

    @Test
    @Description("Create book for deleted user")
    public void createBookForDeletedUser() {
        final AtomicReference<UserDTO> user = new AtomicReference<>();

        step("GIVEN a new user of status A", () -> {
            user.set(getUserService().initContextUser("A"));
            final ResponseEntity<CreateUser201ResponseDTO> response = getUserService().registerUser(user.get());
            assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.CREATED));
        });
    }
}
