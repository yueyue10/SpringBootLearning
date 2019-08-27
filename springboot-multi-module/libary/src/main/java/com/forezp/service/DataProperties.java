package com.forezp.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("data")
public class DataProperties {

    /**
     * A message for the service.
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}