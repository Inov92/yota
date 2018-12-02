package com.yota.ta.core.services.screens.elements;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class MainNavigationBar extends Widget {

    @AndroidFindBy(accessibility = "Explore")
    private MobileElement explore;

    @AndroidFindBy(accessibility = "My lists")
    private MobileElement myLists;

    @AndroidFindBy(accessibility = "History")
    private MobileElement history;

    @AndroidFindBy(accessibility = "Nearby")
    private MobileElement nearby;



    protected MainNavigationBar(WebElement element) {
        super(element);
    }
}
