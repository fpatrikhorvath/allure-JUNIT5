package com.framework.allure.stores;


import com.framework.allure.service.BookService;
import com.framework.allure.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserLayerContextStore {
    private final UserService userService;
    private final BookService bookService;

    public UserLayerContextStore(final UserService userService,
                                 final BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    public UserService getUserService() {
        return userService;
    }

    public BookService getBookService() {
        return bookService;
    }
}
