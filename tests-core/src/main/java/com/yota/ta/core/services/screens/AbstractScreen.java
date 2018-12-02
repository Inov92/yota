package com.yota.ta.core.services.screens;

import com.yota.ta.core.services.actions.AppiumActions;
import com.yota.ta.core.services.screens.elements.MainNavigationBar;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public abstract class AbstractScreen {

    @Autowired
    @Lazy
    public AppiumActions driver;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='org.wikipedia:id/fragment_main_nav_tab_layout']/android.view.ViewGroup")
    public MainNavigationBar mainNavigationBar;

    public void tapExplore(){
        driver.singleTap(mainNavigationBar.getExplore());
    }

    public void tapMyLists() {
        driver.singleTap(mainNavigationBar.getMyLists());
    }

    public void tapHistory() {
        driver.singleTap(mainNavigationBar.getHistory());
    }

    public void tapNearby() {
        driver.singleTap(mainNavigationBar.getNearby());
    }
    public abstract boolean isLoaded();
}
