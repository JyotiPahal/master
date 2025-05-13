package com.example.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
// import io.appium.java_client.pagefactory.iOSXCUITFindBy; // Commented out as not used
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage {

    private AppiumDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Welcome')]") 
    private WebElement welcomeTextElement;

    @AndroidFindBy(id = "com.ebay.mobile:id/edit_text_username") 
    private WebElement usernameField;

    @AndroidFindBy(id = "com.ebay.mobile:id/password_input_text") 
    private WebElement passwordField;

    @AndroidFindBy(id = "com.ebay.mobile:id/button_sign_in") 
    private WebElement signInButtonOnPage; 

    @AndroidFindBy(id = "com.ebay.mobile:id/tv_sign_in_alert")
    private WebElement signInAlertTextElement;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='close']")
    private WebElement crossButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Simplify your sign-in']") 
    private WebElement simplifySignInTextElement;

    @AndroidFindBy(id = "com.ebay.mobile:id/bt_maybe_later") 
    private WebElement skipForNowButton;

    @AndroidFindBy(id = "com.ebay.mobile:id/bt_maybe_later") 
    private WebElement isSkipForNowButtonClickable;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public boolean isWelcomeTextDisplayedOnSignInPage() {
        try {
            return welcomeTextElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUsernameFieldDisplayed() {
        try {
            return usernameField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
     public boolean isUsernameFieldEnabled() {
        try {
            return usernameField.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterUsername(String username) {
        usernameField.click(); // Ensure focus
        usernameField.sendKeys(username);
    }

    public boolean isPasswordFieldDisplayed() {
        try {
            return passwordField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordFieldEnabled() {
        try {
            return passwordField.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public boolean isSignInButtonOnSignInPageDisplayed() {
        try {
            return signInButtonOnPage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSignInButtonOnSignInPageEnabled() {
        try {
            return signInButtonOnPage.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSignInButtonOnSignInPage() {
        signInButtonOnPage.click();
    }

    public String getSignInAlertText() {
        try {
            return signInAlertTextElement.getText();
        } catch (Exception e) {
            return ""; 
        }
    }

    public boolean isSimplifySignInTextDisplayed() {
        try {
            return simplifySignInTextElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isSkipForNowButtonClickable() {
        try {
            return skipForNowButton.isDisplayed() && skipForNowButton.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickSkipForNowButton() {
        skipForNowButton.click();
    }

    public void clickCrossButtonOnSignInPage() {
        crossButton.click();
    }
} 