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
        @PropertySource("classpath:ci.properties"),
        @PropertySource("classpath:${TA_DEVICE:android9}.properties")
})
public class AppConfig {
}
