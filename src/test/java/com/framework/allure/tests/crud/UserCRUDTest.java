package com.framework.allure.tests.crud;

import com.framework.allure.core.TestCore;
import com.framework.allure.rest.response.CreateUser201ResponseDTO;
import com.framework.allure.rest.response.UserDTO;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.*;

@Execution(ExecutionMode.CONCURRENT)
public class UserCRUDTest extends TestCore {

    @Test
    @Description("Create user")
    public void createUser() {
        final AtomicReference<UserDTO> contextUser = new AtomicReference<>();

        step("WHEN a new user of status A", () -> {
            contextUser.set(getUserService().initContextUser("A"));
            final ResponseEntity<CreateUser201ResponseDTO> response = getUserService().registerUser(contextUser.get());
            assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.CREATED));
        });

        step("THEN verify that user exists", () -> {
            final ResponseEntity<List<UserDTO>> response = getUserService().getUsers();
            assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.OK));

            final UserDTO actUser = Objects.requireNonNull(response.getBody()).stream().filter(u -> Objects.equals(u.getName(), contextUser.get().getName())).findFirst().orElse(null);
            assertEquals(actUser.getName(), contextUser.get().getName());
            assertEquals(actUser.getEmail(), contextUser.get().getEmail());
            assertEquals(actUser.getStatus(), contextUser.get().getStatus());
        });
    }

    @Test
    @Description("Delete user")
    @Disabled("Not deleting the books of the deleted user")
    public void deleteUser() {
        final AtomicReference<UserDTO> contextUser = new AtomicReference<>();

        step("GIVEN create a new user of status A", () -> {
            contextUser.set(getUserService().initContextUser("A"));
            final ResponseEntity<CreateUser201ResponseDTO> response = getUserService().registerUser(contextUser.get());
            assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.CREATED));
            contextUser.get().setId(Objects.requireNonNull(response.getBody()).getId());
        });

        step("WHEN delete user", () -> {
            final ResponseEntity<Void> response = getUserService().deleteUser(contextUser.get().getId());
            assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.NO_CONTENT));
        });

        step("THEN verify that user does not exist", () -> {
            final ResponseEntity<List<UserDTO>> response = getUserService().getUsers();
            assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.OK));
            final UserDTO actUser = Objects.requireNonNull(response.getBody()).stream().filter(u ->
                    Objects.equals(u.getName(), contextUser.get().getName())).findFirst().orElse(null);
            assertNull(actUser);
        });
    }
}
