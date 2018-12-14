package com.guanbad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       // registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/logout").setViewName("login");
        registry.addViewController("/login").setViewName("login");
    }
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}