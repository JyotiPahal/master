package com.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private Properties properties;

    public PropertyReader(String filePath) {
        properties = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException ex) {
            System.err.println("Error loading properties file: " + filePath);
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            return value.trim();
        }
        System.err.println("Property not found: " + key);
        return null;
    }

    public String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key, defaultValue);
         if (value != null) {
            return value.trim();
        }
        return defaultValue; 
    }
    
    public int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing integer for key: " + key + ", value: " + value + ". Using default: " + defaultValue);
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }
} 