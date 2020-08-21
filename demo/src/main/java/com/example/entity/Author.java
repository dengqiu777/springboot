package com.example.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "author")
public class Author {
    private  String name;

    private  String csdnUrl;

    public String getCsdnUrl() {
        return csdnUrl;
    }

    public void setCsdnUrl(String csdnUrl) {
        this.csdnUrl = csdnUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
