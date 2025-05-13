package com.example.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class RegisterPage {

    private AppiumDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Create an account')]") 
    private WebElement createAccountTextElement;

    @AndroidFindBy(id = "com.ebay.mobile:id/et_email") 
    private WebElement emailField;

    @AndroidFindBy(id = "com.ebay.mobile:id/et_first_name") 
    private WebElement firstNameField;

    @AndroidFindBy(id = "com.ebay.mobile:id/et_last_name") 
    private WebElement lastNameField;

    @AndroidFindBy(id = "com.ebay.mobile:id/button_continue") 
    private WebElement continueButton;

    // --- Elements for Create Password Page ---
    @AndroidFindBy(id = "com.ebay.mobile:id/tv_welcome") 
    private WebElement createPasswordPageTitle;

    @AndroidFindBy(id = "com.ebay.mobile:id/password_input_text") 
    private WebElement passwordFieldCreatePage;

    @AndroidFindBy(id = "com.ebay.mobile:id/tv_password_complexity") 
    private WebElement passwordComplexityTextElement;

    @AndroidFindBy(id = "com.ebay.mobile:id/tv_legal_text") 
    private WebElement legalTextElement;

    @AndroidFindBy(id = "com.ebay.mobile:id/bt_user_agreement")
    private WebElement userAgreementLinkElement;

    @AndroidFindBy(id = "com.ebay.mobile:id/bt_user_privacy_notice") 
    private WebElement privacyNoticeLinkElement;

    @AndroidFindBy(id = "com.ebay.mobile:id/button_continue")
    private WebElement createAccountButtonOnPasswordPage;

    // --- Elements for Add Phone Number Page ---
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Add your phone number')]") 
    private WebElement addPhoneNumberTitleElement;

    @AndroidFindBy(id = " com.ebay.mobile:id/tv_description")
    private WebElement phoneNumberDescriptionTextElement;

    @AndroidFindBy(id = " com.ebay.mobile:id/phone") 
    private WebElement phoneNumberEditBox;

    @AndroidFindBy(id = "com.ebay.mobile:id/button_continue") 
    private WebElement continueButtonPhonePage;

    // --- Elements for Enter Verification Code Page ---
    @AndroidFindBy(id= "com.ebay.mobile:id/verification_code")
    private WebElement enterVerificationCodeTextElement;

    @AndroidFindBy(id = "com.ebay.mobile:id/button_continue") 
    private WebElement continueButtonVerificationPage;


    public RegisterPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    // Methods for initial registration part (email, name)
    public boolean isCreateAccountTextDisplayed() {
        try {
            return createAccountTextElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmailFieldClickable() {
        try {
            return emailField.isDisplayed() && emailField.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterEmail(String email) {
        emailField.click();
        emailField.sendKeys(email);
    }

    public boolean isFirstNameFieldClickable() {
        try {
            return firstNameField.isDisplayed() && firstNameField.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterFirstName(String firstName) {
        firstNameField.click();
        firstNameField.sendKeys(firstName);
    }

    public boolean isLastNameFieldClickable() {
        try {
            return lastNameField.isDisplayed() && lastNameField.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterLastName(String lastName) {
        lastNameField.click();
        lastNameField.sendKeys(lastName);
    }

    public boolean isContinueButtonVisibleAndClickable() {
        try {
            return continueButton.isDisplayed() && continueButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    // --- Methods for Create Password Page ---
    public boolean isCreatePasswordPageDisplayed() {
        try {
            return createPasswordPageTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordTextBoxClickableOnCreatePasswordPage() {
        try {
            return passwordFieldCreatePage.isDisplayed() && passwordFieldCreatePage.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterPasswordOnCreatePasswordPage(String password) {
        passwordFieldCreatePage.click();
        passwordFieldCreatePage.sendKeys(password);
    }

    public boolean isPasswordComplexityTextDisplayed() {
        try {
            return passwordComplexityTextElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLegalTextDisplayed() {
        try {
            return legalTextElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUserAgreementTextDisplayed() {
        try {
            return userAgreementLinkElement.isDisplayed(); 
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPrivacyNoticeTextDisplayed() {
        try {
            return privacyNoticeLinkElement.isDisplayed(); 
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCreateAccountButtonOnPasswordPageClickable() {
        try {
            return createAccountButtonOnPasswordPage.isDisplayed() && createAccountButtonOnPasswordPage.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCreateAccountButtonOnPasswordPage() {
        createAccountButtonOnPasswordPage.click();
    }

    // --- Methods for Add Phone Number Page ---
    public boolean isAddPhoneNumberTitleDisplayed() {
        try {
            return addPhoneNumberTitleElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPhoneNumberDescriptionTextDisplayed() {
        try {
            return phoneNumberDescriptionTextElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPhoneNumberEditBoxClickable() {
        try {
            return phoneNumberEditBox.isDisplayed() && phoneNumberEditBox.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberEditBox.click();
        phoneNumberEditBox.sendKeys(phoneNumber);
    }

    public boolean isContinueButtonOnAddPhonePageClickable() {
        try {
            return continueButtonPhonePage.isDisplayed() && continueButtonPhonePage.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickContinueButtonOnAddPhonePage() {
        continueButtonPhonePage.click();
    }

    // --- Methods for Enter Verification Code Page ---
    public boolean isEnterVerificationCodeTextDisplayed() {
        try {
            return enterVerificationCodeTextElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isContinueButtonOnVerificationPageClickable() {
        try {
            return continueButtonVerificationPage.isDisplayed() && continueButtonVerificationPage.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickContinueButtonOnVerificationPage() {
        continueButtonVerificationPage.click();
    }
} 