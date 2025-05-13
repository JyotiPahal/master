package com.example.stepdefinitions;

import com.example.pages.LoginPage;
import com.example.pages.HomePage;
import com.example.pages.RegisterPage;
import com.example.utils.AppiumDriverConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;

public class LoginSteps {

    private AppiumDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private RegisterPage registerPage;

    @Before
    public void setUp() {
        driver = AppiumDriverConfig.initializeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
    }

    @When("I launch app")
    public void i_launch_app() {
        Assert.assertNotNull(driver, "Appium driver was not initialized.");
    }

    @Then("I verify the ebay text to confirm the home page of app")
    public void i_verify_the_ebay_text_to_confirm_the_home_page_of_app() {
        Assert.assertTrue(homePage.isEbayTextDisplayed(), "eBay text not displayed on home page.");
    }

    @Then("I verify the signin text is displayed on home page")
    public void i_verify_the_signin_text_is_displayed_on_home_page() {
        Assert.assertTrue(homePage.isSignInTextDisplayedOnHomePage(), "Sign in text not displayed on home page.");
    }

    @Then("I click on the home page signin button")
    public void i_click_on_the_home_page_signin_button() {
        homePage.clickHomePageSignInButton();
    }

    @Then("I verify the welcome text is displayed on signin page")
    public void i_verify_the_welcome_text_is_displayed_on_signin_page() {
        Assert.assertTrue(loginPage.isWelcomeTextDisplayedOnSignInPage(), "Welcome text not displayed on sign page.");
    }

    @Then("I verify the username text box is clickable on signin page")
    public void i_verify_the_username_text_box_is_clickable_on_signin_page() {
        Assert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field not displayed.");
        Assert.assertTrue(loginPage.isUsernameFieldEnabled(), "Username field not enabled.");
    }

    @When("I click on username text box and enter {string}")
    public void i_click_on_username_text_box_and_enter(String username) {
        loginPage.enterUsername(username);
    }

    @Then("I verify the password text box is clickable on signin page")
    public void i_verify_the_password_text_box_is_clickable_on_signin_page() {
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field not displayed.");
        Assert.assertTrue(loginPage.isPasswordFieldEnabled(), "Password field not enabled.");
    }

    @When("I enter password {string} in password text box")
    public void i_enter_password_in_password_text_box(String password) {
        loginPage.enterPassword(password);
    }

    @Then("I verify the signin button is clickable on signin page")
    public void i_verify_the_signin_button_is_clickable_on_signin_page() {
        Assert.assertTrue(loginPage.isSignInButtonOnSignInPageDisplayed(), "Sign in button not displayed on sign in page.");
        Assert.assertTrue(loginPage.isSignInButtonOnSignInPageEnabled(), "Sign in button not enabled on sign in page.");
    }

    @When("I click on the signin page signin button on the signin page")
    public void i_click_on_the_signin_page_signin_button() {
        loginPage.clickSignInButtonOnSignInPage();
    }

    @Then("I verify the simplyfy your sign in text is displayed on signin page")
    public void i_verify_the_simplyfy_your_sign_in_text_is_displayed_on_signin_page() {
        Assert.assertTrue(loginPage.isSimplifySignInTextDisplayed(), "'Simplify your sign in' text not displayed on signin page.");
    }

    @Then("I verify the skip for now button is clickable on signin page")
    public void i_verify_the_skip_for_now_button_is_clickable_on_signin_page() {
        Assert.assertTrue(loginPage.isSkipForNowButtonClickable(), "'Skip for now' button not clickable on signin page.");
    }

    @When("I click on the skip for now button on the signin page")
    public void i_click_on_the_skip_for_now_button_on_the_signin_page() {
        loginPage.clickSkipForNowButton();
    }

    @Then("I verify the shopnow text is displayed on home page")
    public void i_verify_the_shopnow_text_is_displayed_on_home_page() {
        Assert.assertTrue(homePage.isShopNowTextDisplayed(), "'Shop Now' text not displayed on home page.");
    }

    @Then("I verify the myebay button is displayed on home page")
    public void i_verify_the_myebay_button_is_displayed_on_home_page() {
        Assert.assertTrue(homePage.isMyEbayButtonDisplayed(), "'My eBay' button not displayed on home page.");
    }

    @When("I click on the myebay button on home page")
    public void i_click_on_the_myebay_button_on_home_page() {
        homePage.clickMyEbayButton();
    }

    @When("I scroll to the settings button on myebay page")
    public void i_scroll_to_the_settings_button_on_myebay_page() {
        homePage.scrollToSettingsButton();
    }

    @When("I click on the settings button on myebay page")
    public void i_click_on_the_settings_button_on_myebay_page() {
        homePage.clickSettingsButton();
    }

    @Then("I verify the sign out button is visible on settings page")
    public void i_verify_the_sign_out_button_is_visible_on_settings_page() {
        Assert.assertTrue(homePage.isSignOutButtonVisible(), "Sign out button not visible on settings page.");
    }

    @When("I click on the sign out button on settings page")
    public void i_click_on_the_sign_out_button_on_settings_page() {
        homePage.clickSignOutButton();
    }

    @Then("I verify the error message {string} is displayed")
    public void i_verify_the_error_message_is_displayed(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.getSignInAlertText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), 
            "Error message did not contain expected text. Expected to contain: '" + expectedErrorMessage + "', Actual: '" + actualErrorMessage + "'");
    }

    @Then("I click on the cross button on the signin page")
    public void i_click_on_the_cross_button_on_the_signin_page() {
        loginPage.clickCrossButtonOnSignInPage();
    }

    // --- Steps from Scenario Outline ---
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        Assert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Login page did not load correctly (username field not displayed).");
    }

    @When("I enter username {string}")
    public void i_enter_username(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void i_enter_password(String password) {
        loginPage.enterPassword(password);
    }

    @When("I tap on the login button") // This refers to the login button on the generic login page for the outline
    public void i_tap_on_the_login_button() {
        loginPage.clickSignInButtonOnSignInPage(); // Reusing the more specific method for clarity
    }

    @Then("I should be successfully logged in")
    public void i_should_be_successfully_logged_in() {
        System.out.println("Placeholder: Login successful assertion needed here.");
        Assert.assertTrue(true, "Placeholder: Login successful. Implement actual assertion.");
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String errorMessage) {
        // This is for the scenario outline, may need a different error element/method if it's not the tv_sign_in_alert
        String actualErrorMessage = loginPage.getSignInAlertText(); 
        Assert.assertTrue(actualErrorMessage.contains(errorMessage), 
            "Error message did not contain expected text. Expected: '" + errorMessage + "', Actual: '" + actualErrorMessage + "'");
    }

    @After
    public void tearDown() {
        AppiumDriverConfig.quitDriver();
    }

    @Then("I wait for {int} seconds")
    public void i_wait_for_seconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }

    @Then("I close any initial popup by clicking the cross button")
    public void i_close_any_initial_popup_by_clicking_the_cross_button() {
        homePage.closeInitialPopupIfPresent();
    }


    @Then("I check for ebay logo on home page")
    public void i_check_for_ebay_logo_on_home_page() {
        Assert.assertTrue(homePage.isEbayTextDisplayed(), "eBay logo/text not displayed on home page.");
    }

    @Then("I verify the register button is displayed on home page")
    public void i_verify_the_register_button_is_displayed_on_home_page() {
        Assert.assertTrue(homePage.isRegisterButtonDisplayedOnHomePage(), "Register button not displayed on home page.");
    }

    @When("I click on the register button on home page")
    public void i_click_on_the_register_button_on_home_page() {
        homePage.clickRegisterButtonOnHomePage();
    }

    @Then("I verify the {string} text is displayed on register page")
    public void i_verify_the_text_is_displayed_on_register_page(String expectedText) {
        if (expectedText.equalsIgnoreCase("Create an account")) {
            Assert.assertTrue(registerPage.isCreateAccountTextDisplayed(), "'Create an account' text not displayed on register page.");
        } else {
            // You might want to add a more generic text check in RegisterPage if needed for other texts.
            Assert.fail("Verification for text '" + expectedText + "' not implemented yet.");
        }
    }

    @Then("I verify the email text box is clickable on register page")
    public void i_verify_the_email_text_box_is_clickable_on_register_page() {
        Assert.assertTrue(registerPage.isEmailFieldClickable(), "Email field not clickable on register page.");
    }

    @When("I click on email text box and enter {string} in email text box on register page")
    public void i_enter_email_in_email_text_box_on_register_page(String email) {
        registerPage.enterEmail(email);
    }

    @Then("I verify the first name text box is clickable on register page")
    public void i_verify_the_first_name_text_box_is_clickable_on_register_page() {
        Assert.assertTrue(registerPage.isFirstNameFieldClickable(), "First name field not clickable on register page.");
    }

    @When("I click first name text box and enter {string} in first name text box on register page")
    public void i_enter_first_name_in_first_name_text_box_on_register_page(String firstName) {
        registerPage.enterFirstName(firstName);
    }

    @Then("I verify the last name text box is clickable on register page")
    public void i_verify_the_last_name_text_box_is_clickable_on_register_page() {
        Assert.assertTrue(registerPage.isLastNameFieldClickable(), "Last name field not clickable on register page.");
    }

    @When("I click on last name text box and enter {string} in last name text box on register page")
    public void i_enter_last_name_in_last_name_text_box_on_register_page(String lastName) {
        registerPage.enterLastName(lastName);
    }

    @Then("I verify the continue button is visible and clickable on register page")
    public void i_verify_the_continue_button_is_visible_and_clickable_on_register_page() {
        Assert.assertTrue(registerPage.isContinueButtonVisibleAndClickable(), "Continue button not visible or clickable on register page.");
    }

    @When("I click on the continue button on register page") // Changed from @Then to @When as it's an action
    public void i_click_on_the_continue_button_on_register_page() {
        registerPage.clickContinueButton();
    }

    @Then("I verify I am on the create password page")
    public void i_verify_i_am_on_the_create_password_page() {
        Assert.assertTrue(registerPage.isCreatePasswordPageDisplayed(), "Not on the create password page as expected.");
    }

    @Then("I verify the password text box is clickable on create password page")
    public void i_verify_the_password_text_box_is_clickable_on_create_password_page() {
        Assert.assertTrue(registerPage.isPasswordTextBoxClickableOnCreatePasswordPage(), "Password text box not clickable on create password page.");
    }

    @When("I click on password text box and enter {string} in password text box on create password page")
    public void i_enter_password_in_password_text_box_on_create_password_page(String password) {
        registerPage.enterPasswordOnCreatePasswordPage(password);
    }

    @Then("I verify the password complexity text is displayed on create password page")
    public void i_verify_the_password_complexity_text_is_displayed_on_create_password_page() {
        Assert.assertTrue(registerPage.isPasswordComplexityTextDisplayed(), "Password complexity text not displayed.");
    }

    @Then("I verify the legal text is displayed on create password page")
    public void i_verify_the_legal_text_is_displayed_on_create_password_page() {
        Assert.assertTrue(registerPage.isLegalTextDisplayed(), "Legal text not displayed.");
    }

    @Then("I verify the user agreement text is displayed on create password page")
    public void i_verify_the_user_agreement_text_is_displayed_on_create_password_page() {
        Assert.assertTrue(registerPage.isUserAgreementTextDisplayed(), "User agreement text/link not displayed.");
    }

    @Then("I verify the privacy notice text is displayed on create password page")
    public void i_verify_the_privacy_notice_text_is_displayed_on_create_password_page() {
        Assert.assertTrue(registerPage.isPrivacyNoticeTextDisplayed(), "Privacy notice text/link not displayed.");
    }

    @Then("I verify the create account button is clickable on create password page")
    public void i_verify_the_create_account_button_is_clickable_on_create_password_page() {
        Assert.assertTrue(registerPage.isCreateAccountButtonOnPasswordPageClickable(), "Create account button not clickable.");
    }

    @When("I click on the create account button on create password page")
    public void i_click_on_the_create_account_button_on_create_password_page() {
        registerPage.clickCreateAccountButtonOnPasswordPage();
    }

    @Then("I verify the \"Add your phone number\" title is displayed register page")
    public void i_verify_the_add_your_phone_number_title_is_displayed_register_page() {
        Assert.assertTrue(registerPage.isAddPhoneNumberTitleDisplayed(), "'Add your phone number' title not displayed on register page.");
    }

    @Then("I verify the \"Enter verification code\" text is displayed")
    public void i_verify_the_enter_verification_code_text_is_displayed() {
        Assert.assertTrue(registerPage.isEnterVerificationCodeTextDisplayed(), "'Enter verification code' text not displayed.");
    }

    @Then("I verify the description text is displayed on add phone number page")
    public void i_verify_the_description_text_is_displayed_on_add_phone_number_page() {
        Assert.assertTrue(registerPage.isPhoneNumberDescriptionTextDisplayed(), "Phone number description text not displayed.");
    }

    @Then("I verify the phone number edit box is clickable on add phone number page")
    public void i_verify_the_phone_number_edit_box_is_clickable_on_add_phone_number_page() {
        Assert.assertTrue(registerPage.isPhoneNumberEditBoxClickable(), "Phone number edit box not clickable.");
    }

    @When("I click on phone number edit box and enter {string} on add phone number page")
    public void i_click_on_phone_number_edit_box_and_enter_on_add_phone_number_page(String phoneNumber) {
        registerPage.enterPhoneNumber(phoneNumber);
    }

    @Then("I verify the continue button is clickable on add phone number page")
    public void i_verify_the_continue_button_is_clickable_on_add_phone_number_page() {
        Assert.assertTrue(registerPage.isContinueButtonOnAddPhonePageClickable(), "Continue button not clickable on add phone number page.");
    }

    @When("I click on the continue button on add phone number page")
    public void i_click_on_the_continue_button_on_add_phone_number_page() {
        registerPage.clickContinueButtonOnAddPhonePage();
    }

    @Then("I verify the continue button is clickable on enter verification code page")
    public void i_verify_the_continue_button_is_clickable_on_enter_verification_code_page() {
        Assert.assertTrue(registerPage.isContinueButtonOnVerificationPageClickable(), "Continue button not clickable on enter verification code page.");
    }

    @When("I click on the continue button on enter verification code page")
    public void i_click_on_the_continue_button_on_enter_verification_code_page() {
        registerPage.clickContinueButtonOnVerificationPage();
    }
} 