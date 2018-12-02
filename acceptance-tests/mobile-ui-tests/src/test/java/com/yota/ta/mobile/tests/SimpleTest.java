package com.yota.ta.mobile.tests;

import com.yota.ta.mobile.configuration.AppConfig;
import io.appium.java_client.AppiumDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@ContextConfiguration(classes = {AppConfig.class})
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class SimpleTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @Lazy
    private AppiumDriver driver;

    @Test
    public void test() {
        System.out.println(driver.getContext());
    }

}
