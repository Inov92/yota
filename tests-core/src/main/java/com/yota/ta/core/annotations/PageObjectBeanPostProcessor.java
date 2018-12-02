package com.yota.ta.core.annotations;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Initialization of mobile page object.
 * Classes annotated with {@link MobilePageObject}
 * will be initialized with AppiumDriver as page objects
 */
@Component
public class PageObjectBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    @Lazy
    private AppiumDriver driver;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(MobilePageObject.class)) {
            PageFactory.initElements(new AppiumFieldDecorator(driver), bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
