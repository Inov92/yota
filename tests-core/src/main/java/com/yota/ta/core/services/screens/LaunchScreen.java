package com.yota.ta.core.services.screens;

import com.yota.ta.core.annotations.MobilePageObject;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

@MobilePageObject
public class LaunchScreen extends AbstractScreen {

    @AndroidFindBy(id = "org.wikipedia:id/fragment_onboarding_skip_button")
    private MobileElement skipButton;

    public void tapSkipButton() {
        driver.singleTap(skipButton);
    }

    @Override
    public boolean isLoaded() {
        return driver.isElementDisplayed(skipButton);
    }
}
