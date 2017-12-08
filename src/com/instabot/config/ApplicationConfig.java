package com.instabot.config;

import com.instabot.service.LoginService;
import com.instabot.service.impl.LoginServiceImpl;
import com.instabot.service.PreferencesService;
import com.instabot.service.impl.PreferencesServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "com.instabot")
public class ApplicationConfig {

    @Bean
    public LoginService loginService() {
        return new LoginServiceImpl();
    }

    @Bean
    public PreferencesService preferencesService() {
        return new PreferencesServiceImpl();
    }
}
