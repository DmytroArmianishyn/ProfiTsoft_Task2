package com.example.lab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.lab")
public class MyConfig implements WebMvcConfigurer {

    private final ApplicationContext context;

    @Autowired
    public MyConfig(ApplicationContext context) {
        this.context = context;
    }

}
