package com.instabot.service;

import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.prefs.Preferences;

@Service
public class PreferencesServiceImpl implements PreferencesService {

    @Override
    public void save(final String key, final String value) {
        final Preferences prefs = Preferences.userNodeForPackage(this.getClass());
        if (key != null && !Objects.equals(key, "")) {
            if (value != null && !Objects.equals(value, "")) {
                prefs.put(key, value);
            } else {
                prefs.remove(key);
            }
        } else {
            throw new IllegalArgumentException("'key' cannot be null or empty!");
        }
    }

    @Override
    public String get(final String key) {
        final Preferences prefs = Preferences.userNodeForPackage(this.getClass());
        if (key != null && !Objects.equals(key, "")) {
            return prefs.get(key, null);
        }
        return null;
    }
}
