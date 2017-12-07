package com.instabot.service;

import org.brunocvcunha.instagram4j.Instagram4j;

/**
 * @author Maxym Borodenko
 */
public interface LoginService {

    Instagram4j login(String username, String password);
}
