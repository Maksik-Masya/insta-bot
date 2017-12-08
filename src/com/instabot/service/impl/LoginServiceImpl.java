package com.instabot.service.impl;

import com.instabot.service.LoginService;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public Instagram4j login(final String username, final String password) {
        final Instagram4j instagram = Instagram4j.builder().username(username).password(password).build();
        instagram.setup();
        try {
            instagram.login();
        } catch (final IOException e) {
            return null;
        }
        return instagram;
    }
}
