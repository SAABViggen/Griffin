package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties("info.company")
public class CompanyData {

    private String name, goal, email, phone;
}