package com.instabot.service;

public interface PreferencesService {

    String USER_USERNAME = "username";
    String USER_PASSWORD = "password";
    String SAVE_CREDENTIALS =  "saveCredentials";

    void save(String key, String value);

    String get(String key);
}
