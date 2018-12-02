package com.yota.ta.core.services.screens;

import com.yota.ta.core.annotations.MobilePageObject;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

@MobilePageObject
public class ArticleScreen extends AbstractScreen {

    @AndroidFindBy(accessibility = "Navigate up")
    public MobileElement returnButton;

    public void tapReturnButton() {
        driver.singleTap(returnButton);
    }

    @Override
    public boolean isLoaded() {
        return driver.isElementDisplayed(returnButton);
    }
}
