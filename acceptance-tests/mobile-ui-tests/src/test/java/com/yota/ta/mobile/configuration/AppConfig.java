package com.yota.ta.mobile.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@RequiredArgsConstructor
@ComponentScan({"com.yota.ta.core"})
@PropertySources({
        @PropertySource("classpath:env/${TA_ENV:local}.properties"),
        @PropertySource("classpath:devices/${TA_DEVICE:android9}.properties")
})
public class AppConfig {
}
