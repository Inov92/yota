package com.yota.ta.core.services.screens;

import com.yota.ta.core.annotations.MobilePageObject;

@MobilePageObject
public class HistoryScreen extends AbstractScreen{

    @Override
    public boolean isLoaded() {
        return driver.isTextDisplayed("History");
    }
}
