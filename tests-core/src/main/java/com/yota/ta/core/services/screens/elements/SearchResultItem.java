package com.yota.ta.core.services.screens.elements;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@AndroidFindBy(className = "android.view.ViewGroup")
public class SearchResultItem extends Widget {

    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='org.wikipedia:id/page_list_item_image']")
    private MobileElement itemImage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']")
    private MobileElement itemTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description']")
    private MobileElement itemDescription;

    protected SearchResultItem(WebElement element) {
        super(element);
    }
}
