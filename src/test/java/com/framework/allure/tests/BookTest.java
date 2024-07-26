package com.framework.allure.tests;

import com.framework.allure.core.TestCore;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class BookTest extends TestCore {

//    @Test
//    @Description("Create book for deleted user")
//    public void createBookForDeletedUser() {
//        final AtomicReference<UserDTO> user = new AtomicReference<>();
//
//        step("GIVEN a new user of status A", () -> {
//            user.set(getUserService().initContextUser("A"));
//            final ResponseEntity<CreateUser201ResponseDTO> response = getUserService().registerUser(user.get());
//            assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.CREATED));
//        });
//
//
//    }
}
