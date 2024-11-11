package ru.innotech.education.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LimitServiceProperties.class)
public class LimitServiceConfiguration {

    private final LimitServiceProperties properties;

    public LimitServiceConfiguration(LimitServiceProperties properties) {
        this.properties = properties;
    }

    public Double getLimit() {
        return properties.getLimit();
    }
}
