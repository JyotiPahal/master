package com.example.utils;

import java.net.URL;
import java.time.Duration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;

public class AppiumDriverConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppiumDriverConfig.class);

    private static AppiumDriver driver;
    private static PropertyReader propertyReader;
    private static final String DEFAULT_PROPERTIES_PATH = "src/test/resources/config/android.properties";

    // Load properties based on platform or a system property
    private static PropertyReader getPropertyReader() {
        // Potentially extend to load different files e.g. ios.properties
        String propertiesFilePath = System.getProperty("caps.properties", DEFAULT_PROPERTIES_PATH);
        if (propertyReader == null || !propertiesFilePath.equals(propertyReader.getProperty("_loadedFile"))) { // crude way to check if we loaded the right file
            propertyReader = new PropertyReader(propertiesFilePath);
            // Store the loaded file path to avoid reloading if not necessary, ideally PropertyReader handles this
        }
        return propertyReader;
    }

    public static AppiumDriver initializeDriver() {
        if (driver == null) {
            logger.info("Initializing Appium driver...");
            PropertyReader props = getPropertyReader();

            String platformName = System.getProperty("platformName", props.getProperty("platformName", "Android"));
            String appiumServerUrl = System.getProperty("appiumServerUrl", props.getProperty("appiumServerUrl", "http://127.0.0.1:4723"));

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", platformName);
            caps.setCapability("platformVersion", props.getProperty("platformVersion"));
            caps.setCapability("deviceName", props.getProperty("deviceName"));
            caps.setCapability("automationName", props.getProperty("automationName"));

            // BEGIN: ANDROID_HOME override logic
            String androidHome = System.getenv("ANDROID_HOME");
            String correctSdkPath = "/Users/zupee1/Library/Android/sdk";

            if (androidHome == null || androidHome.isEmpty() || !new java.io.File(androidHome, "platform-tools").exists()) {
                System.out.println("WARN: ANDROID_HOME environment variable is not set or invalid ('" + androidHome + "'). " +
                                   "Attempting to use hardcoded SDK path: " + correctSdkPath +
                                   " by setting 'appium:androidSdkPath' capability.");
                caps.setCapability("appium:androidSdkPath", correctSdkPath);
            } else {
                if (!androidHome.equals(correctSdkPath)) {
                    System.out.println("WARN: ANDROID_HOME ('" + androidHome + "') does not match expected path ('" + correctSdkPath + "'). " +
                                       "Consider aligning them. Forcing 'appium:androidSdkPath' to " + correctSdkPath);
                     caps.setCapability("appium:androidSdkPath", correctSdkPath);
                } else {
                     System.out.println("INFO: Using ANDROID_HOME: " + androidHome);
                }
            }
           

          
            String appPackage = props.getProperty("appPackage");
            if (appPackage != null && !appPackage.isEmpty()) {
                caps.setCapability("appPackage", appPackage);
                caps.setCapability("appActivity", props.getProperty("appActivity"));
                
                // Add appWaitActivity and appWaitDuration for potentially slow app starts
                // '*' means wait for any activity to become focused.
                caps.setCapability("appWaitActivity", props.getProperty("appWaitActivity", "*")); 
                caps.setCapability("appWaitDuration", props.getIntProperty("appWaitDuration", 30000)); // Wait up to 30 seconds (30000 ms)
            }

          
            // Optional capabilities from properties file
            if (props.getProperty("noReset") != null) {
                 caps.setCapability("noReset", props.getBooleanProperty("noReset", false));
            }
            if (props.getProperty("fullReset") != null) {
                 caps.setCapability("fullReset", props.getBooleanProperty("fullReset", false));
            }

            caps.setCapability("appium:uiautomator2ServerInstallTimeout", 180000);
            caps.setCapability("adbExecTimeout", 180000);
            caps.setCapability("newCommandTimeout", 180);

            try {
                if ("Android".equalsIgnoreCase(platformName)) {
                    driver = new AndroidDriver(new URL(appiumServerUrl), caps);
                    logger.info("AndroidDriver started successfully.");
                } else if ("iOS".equalsIgnoreCase(platformName)) {
                    // Add iOS specific capabilities from properties if needed
                    // e.g. caps.setCapability("bundleId", props.getProperty("bundleId"));
                    driver = new IOSDriver(new URL(appiumServerUrl), caps);
                    logger.info("IOSDriver started successfully.");
                } else {
                    logger.error("Unsupported platform: {}", platformName);
                    throw new IllegalArgumentException("Unsupported platform: " + platformName);
                }
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(props.getIntProperty("implicitWaitSeconds", 10)));
            } catch (MalformedURLException e) {
                logger.error("Failed to initialize AppiumDriver: {}", e.getMessage(), e);
                throw new RuntimeException("Failed to initialize AppiumDriver: " + e.getMessage(), e);
            } catch (Exception e) {
                logger.error("Exception during AppiumDriver initialization: {}", e.getMessage(), e);
                throw new RuntimeException("Exception during AppiumDriver initialization: " + e.getMessage(), e);
            }
        }
        return driver;
    }

    public static AppiumDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
} 