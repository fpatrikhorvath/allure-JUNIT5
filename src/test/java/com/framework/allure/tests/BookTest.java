package com.framework.allure.tests;

import com.framework.allure.core.TestCore;
import com.framework.allure.rest.response.CreateUser201ResponseDTO;
import com.framework.allure.rest.response.*;
import com.framework.allure.stores.UserLayerContextStore;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.atomic.AtomicReference;

import static io.qameta.allure.Allure.step;
import static org.testng.AssertJUnit.assertTrue;

public class BookTest extends TestCore {

    @Autowired
    private UserLayerContextStore userLayerContextStore;

    @Test
    @Description("Create book for user")
    public void createBookForUser() {
        final AtomicReference<UserDTO> user = new AtomicReference<>();

        step("GIVEN a new user of status A", () -> {
            user.set(getUserService().initContextUser("A"));
            ResponseEntity<CreateUser201ResponseDTO> response = getUserService().registerUser(user.get());
            assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.CREATED));
        });


    }
}
