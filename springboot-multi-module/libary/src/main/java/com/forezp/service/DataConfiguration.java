package com.forezp.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fangzhipeng on 2017/4/19.
 */
@Configuration
@EnableConfigurationProperties(DataProperties.class)
public class DataConfiguration {
    @Bean
    public Service service(DataProperties properties) {
        return new Service(properties.getMessage());
    }
}