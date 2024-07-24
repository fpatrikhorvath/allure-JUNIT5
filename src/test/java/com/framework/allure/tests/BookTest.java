package com.framework.allure.tests;

import com.framework.allure.config.UserLayerConfig;
import com.framework.allure.core.TestCore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BookTest extends TestCore {
    @Autowired
    UserLayerConfig userLayerConfig;

    @Test
    public void testMethod() {
        System.out.println(userLayerConfig.getUrl());
    }
}
