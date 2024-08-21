package com.framework.allure.core;

import com.framework.allure.service.BookService;
import com.framework.allure.service.UserService;
import com.framework.allure.stores.UserLayerContextStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(loader = SpringBootContextLoader.class, value = {"classpath:spring.xml"})
@SpringBootTest
@Configurable
public class TestCore {
    @Autowired
    protected UserLayerContextStore userLayerContextStore;
    protected UserService getUserService() {
        return userLayerContextStore.getUserService();
    }
    protected BookService getBookService() {
        return userLayerContextStore.getBookService();
    }
}