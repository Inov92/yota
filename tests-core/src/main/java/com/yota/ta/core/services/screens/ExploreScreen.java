package com.yota.ta.core.services.screens;

import com.yota.ta.core.annotations.MobilePageObject;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

@MobilePageObject
public class ExploreScreen extends AbstractScreen {

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Search Wikipedia\"])[1]")
    MobileElement searchButton;

    public void tapSearchButton() {
        driver.singleTap(searchButton);
    }

    @Override
    public boolean isLoaded() {
        return driver.isElementDisplayed(searchButton);
    }
}
