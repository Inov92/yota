package com.yota.ta.core.configuration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

@Slf4j
@Configuration
public class AppiumDriverConfig {
    @Value("${driver.driverURL}")
    URL appiumDriverUrl;

    @Value("${app}")
    private String app;

    @Value("${platformName}")
    private String platformName;

    @Value("${deviceName}")
    private String deviceName;

    @Value("${platformVersion}")
    private String platformVersion;

    @Value("${udid}")
    private String udid;

    @Value("${avd}")
    private String avd;

    private AndroidDriver androidDriver;

    @Bean(destroyMethod = "quit")
    public AppiumDriver appiumDriver() {
        switch (platformName) {
            case "Android":
                return initAndroidDriver();
            default:
                throw new IllegalArgumentException(platformName + " platform is not supported");
        }
    }

    private AndroidDriver initAndroidDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.APP, new File(app));
        capabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability("avd", avd);
        capabilities.setCapability("newCommandTimeout", 180);
        try {
            androidDriver = new AndroidDriver(appiumDriverUrl, capabilities);
        } catch (IllegalStateException e) {
            log.error("Appium driver setting not correct. Check driver url and capabilities values");
            throw e;
        }
        return androidDriver;
    }
}
