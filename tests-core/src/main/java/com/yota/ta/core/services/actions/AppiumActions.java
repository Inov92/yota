package com.yota.ta.core.services.actions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import static java.lang.String.valueOf;
import static org.awaitility.Awaitility.await;

@Slf4j
@Service
@Lazy
public class AppiumActions {

    private static final String LOG_EXECUTION_TIME = "{} | Execution_Time(ms):; {};";

    @Autowired
    @Lazy
    @Getter
    AppiumDriver driver;

    @Value("${timeout}")
    private long globalTimeout;

    public long getElementToBeClickableTimeout() {
        return globalTimeout / 4;
    }

    public long getElementToBeVisibleTimeout() {
        return globalTimeout / 4;
    }

    public long getElementIsDisplayedTimeout() {
        return globalTimeout / 12;
    }

    public long getElementIsNotDisplayedTimeout() {
        return globalTimeout / 36;
    }

    public void singleTap(MobileElement element) {
        try {
            waitForElementToBeClickable(element);
            tapOnElementCenter(element);
        } catch (Exception e) {
            log.error("Error occurred while tapping on element " + element.toString() + "\n" + e.toString());
            throw e;
        }
    }

    public void openKeyboard() {
        driver.getKeyboard();
    }

    public void tapOnElementCenter(MobileElement element) {
        new TouchAction(driver).tap(getElementCenter(element)).perform();
        log.info("Tap action performed on element " + element.toString());
    }

    private PointOption getElementCenter(MobileElement element) {
        int xOffset = element.getCenter().getX();
        int yOffset = element.getCenter().getY();
        return PointOption.point(xOffset, yOffset);
    }

    private void waitForElementToBeClickable(WebElement element) {
        long startTime = System.currentTimeMillis();
        try {
            new WebDriverWait(driver, getElementToBeClickableTimeout())
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementNotVisibleException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .until(ExpectedConditions.elementToBeClickable(element));
        } finally {
            log.debug(LOG_EXECUTION_TIME, "WaitForElementToBeClickable", getExecutionTime(startTime));
        }
    }

    private void waitForElementIsVisible(WebElement element) {
        waitForElementIsVisible(element, getElementToBeVisibleTimeout());
    }

    private void waitForElementIsVisible(WebElement element, long timeout) {
        long startTime = System.currentTimeMillis();
        try {
            new WebDriverWait(driver, timeout)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(ElementNotVisibleException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        } finally {
            log.debug(LOG_EXECUTION_TIME, "WaitForElementToBeVisible", getExecutionTime(startTime));
        }
    }

    public boolean isElementDisplayed(MobileElement element) {
        return isElementDisplayed(element, getElementIsDisplayedTimeout());
    }

    public boolean isElementDisplayed(MobileElement element, long timeout) {
        long startTime = System.currentTimeMillis();
        log.info("Check that element " + element.toString() + " is displayed");
        try {
            waitForElementIsVisible(element, timeout);
            log.info(element.toString() + " is displayed");
            return element.isDisplayed();
        } catch (TimeoutException e) {
            log.info(LOG_EXECUTION_TIME, element.toString() + " is not displayed during ", getExecutionTime(startTime));
            return false;
        }
    }

    public boolean isElementNotDisplayed(MobileElement element) {
        return !isElementDisplayed(element, getElementIsNotDisplayedTimeout());
    }

    public boolean isElementNotDisplayed(MobileElement element, long timeout) {
        return !isElementDisplayed(element, timeout);
    }

    public boolean isElementDisplayed(MobileElement element, boolean elementVisibility) {
        if (elementVisibility) {
            return isElementDisplayed(element);
        }
        return isElementNotDisplayed(element);
    }

    public boolean isElementDisplayed(MobileElement element, boolean elementVisibility, long timeout) {
        if (elementVisibility) {
            return isElementDisplayed(element, timeout);
        }
        return isElementNotDisplayed(element, timeout);
    }

    public boolean isElementEnabled(MobileElement element) {
        long startTime = System.currentTimeMillis();
        try {
            log.info("Check that element " + element.toString() + " is enabled");
            waitForElementToBeClickable(element);
            return element.isEnabled();
        } catch (TimeoutException e) {
            log.info(LOG_EXECUTION_TIME, element.toString() + " is not enabled during ", getExecutionTime(startTime));
            return false;
        }
    }


    public void typeData(MobileElement field, String inputText) {
        singleTap(field);
        field.clear();
        field.sendKeys(inputText);
        log.info(inputText + " has been entered to field " + field.toString());
    }

    private String getExecutionTime(long startTime) {
        return valueOf(System.currentTimeMillis() - startTime);
    }

    public void swipeTo(MobileElement element, String direction) {
        waitForElementIsVisible(element);
        JavascriptExecutor js = driver;
        Map<String, Object> params = new HashMap<>();
        params.put("direction", direction);
        params.put("element", element.getId());
        js.executeScript("mobile: swipe", params);
        log.info("Swipe " + direction + " performed on element " + element.toString());
    }

    public void tapByCoordinate(int x, int y) {
        try {
            new TouchAction(driver).tap(PointOption.point(x, y)).perform();
            log.info("Tap action performed by coordinate x = " + x + " y = " + y);
        } catch (Exception e) {
            log.error("Error occurred while tapping by coordinate x = " + x + " y = " + y + "\n" + e.toString());
        }
    }

    public boolean isTextDisplayed(String text) {
        await().atMost(Duration.ONE_MINUTE).until(textIsVisible(text));
        return driver.getPageSource().toLowerCase().contains(text.toLowerCase());
    }

    private Callable<Boolean> textIsVisible(String text) {
        return () -> driver.getPageSource().toLowerCase()
                .contains(text.replace("&", "&amp;").toLowerCase());
    }
}
