package com.yota.ta.mobile.tests;

import com.yota.ta.mobile.configuration.AppConfig;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@ContextConfiguration(classes = {AppConfig.class})
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class SmokeTests extends TestsBase {

    @BeforeMethod
    public void setPreconditions() {
        launchScreen.tapSkipButton();
    }

    @Test
    public void navigationTest() {
        assertThat(exploreScreen.isLoaded()).describedAs("Explore screen was not opened").isTrue();
        exploreScreen.tapSearchButton();
        assertThat(searchScreen.isLoaded()).describedAs("Search screen was not opened").isTrue();
        searchScreen.typeSearchQuery("Yota");
        searchScreen.tapSearchResult(1);
        assertThat(articleScreen.isLoaded()).describedAs("Article screen was not opened").isTrue();
        articleScreen.tapReturnButton();
        assertThat(exploreScreen.isLoaded()).describedAs("Explore screen was not opened").isTrue();
        exploreScreen.tapMyLists();
        assertThat(myListsScreen.isLoaded()).describedAs("My Lists screen was not opened").isTrue();
        myListsScreen.tapHistory();
        assertThat(historyScreen.isLoaded()).describedAs("History screen was not opened").isTrue();
        historyScreen.tapNearby();
        assertThat(nearbyScreen.isLoaded()).describedAs("Nearby screen was not opened").isTrue();
    }
}
