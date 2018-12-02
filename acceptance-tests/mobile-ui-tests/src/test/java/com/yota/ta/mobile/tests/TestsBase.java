package com.yota.ta.mobile.tests;

import com.yota.ta.core.services.screens.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

public abstract class TestsBase extends AbstractTestNGSpringContextTests {

    @Autowired
    @Lazy
    public LaunchScreen launchScreen;

    @Autowired
    @Lazy
    public ExploreScreen exploreScreen;

    @Autowired
    @Lazy
    public SearchScreen searchScreen;

    @Autowired
    @Lazy
    public ArticleScreen articleScreen;

    @Autowired
    @Lazy
    public MyListsScreen myListsScreen;

    @Autowired
    @Lazy
    public HistoryScreen historyScreen;

    @Autowired
    @Lazy
    public NearbyScreen nearbyScreen;
}
