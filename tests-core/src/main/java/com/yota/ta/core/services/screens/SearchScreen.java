package com.yota.ta.core.services.screens;

import com.yota.ta.core.annotations.MobilePageObject;
import com.yota.ta.core.services.screens.elements.SearchResultItem;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

@MobilePageObject
public class SearchScreen extends AbstractScreen {

    @AndroidFindBy(id = "org.wikipedia:id/search_src_text")
    MobileElement searchField;

    @AndroidFindBy(id = "org.wikipedia:id/search_results_list")
    List<SearchResultItem> searchResults;

    public void typeSearchQuery(String searchQuery) {
        driver.typeData(searchField, searchQuery);
    }

    public void tapSearchResult(int itemNumber) {
        driver.singleTap(searchResults.get(itemNumber-1).getItemTitle());
    }

    @Override
    public boolean isLoaded() {
        return driver.isElementDisplayed(searchField);
    }
}
