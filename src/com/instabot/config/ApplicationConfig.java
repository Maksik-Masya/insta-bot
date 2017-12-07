package com.instabot.config;

import com.instabot.service.LoginService;
import com.instabot.service.LoginServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Maxym Borodenko
 */

@Configuration
@ComponentScan(basePackages = "com.instabot")
public class ApplicationConfig {

    @Bean
    public LoginService loginService() {
        return new LoginServiceImpl();
    }
}
