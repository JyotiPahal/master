package com.example.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;

public class HomePage {

    private AppiumDriver driver;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='eBay']") // eBay text
    private WebElement ebayTextElement;

    @AndroidFindBy(id = "com.ebay.mobile:id/button_sign_in") //  Sign in text/button
    private WebElement signInTextElement; 

    @AndroidFindBy(id = "com.ebay.mobile:id/button_sign_in") // This was your existing login button, assuming it's the main sign-in
    private WebElement homePageSignInButton;

    @AndroidFindBy(id = "com.ebay.mobile:id/home_toolbar")
    private WebElement homePageUniqueElement; 

    // Placeholder for a potential initial popup's cross button
    @AndroidFindBy(id = "com.ebay.mobile:id/button_popup_cross") 
    private WebElement initialPopupCrossButton;

    @AndroidFindBy(id = "com.ebay.mobile:id/button_register") 
    private WebElement registerButtonOnHomePage;
// Placeholder for Shop Now text/button
    private WebElement shopNowTextElement;

    // New elements for search and navigation
    @AndroidFindBy(id ="com.ebay.mobile:id/search_src_text") 
    private WebElement searchItem;

    @AndroidFindBy(id ="com.ebay.mobile:id/search_box") 
    private WebElement searchBar;

    @AndroidFindBy(id ="com.ebay.mobile:id/cell_collection_item") 
    private WebElement  productTitleElement;


    @AndroidFindBy(id = "com.ebay.mobile:id/menu_cart") 
    private WebElement shoppingCartLink;

    @AndroidFindBy(accessibility = "My eBay")
    private WebElement myEbayMenuButton; 

    @AndroidFindBy(accessibility = "Purchases") 
    private WebElement orderHistoryLink; 

    @AndroidFindBy(accessibility = "Search") // This locator seems to be for the keyboard search key, not a button on the page.
    private WebElement submitSearchFromKeyboard; 

    
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Categories']")
    private WebElement categoriesButton; 
    // End of new elements

    @AndroidFindBy(id = "com.ebay.mobile:id/uir_bottom_nav_view_menu_action_my_ebay") 
    private WebElement myEbayButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Settings')]") 
    private WebElement settingsButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Sign out')]") 
    private WebElement signOutButton;

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public boolean isHomePageDisplayed() {
        try {
            return homePageUniqueElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEbayTextDisplayed() {
        try {
            return ebayTextElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSignInTextDisplayedOnHomePage() {
        try {
            return signInTextElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickHomePageSignInButton() {
        homePageSignInButton.click();
    }

    public void closeInitialPopupIfPresent() {
        try {
            // Add a short explicit wait if needed, but keep it brief as this is optional
            if (initialPopupCrossButton.isDisplayed()) {
                System.out.println("Initial popup detected, attempting to close.");
                initialPopupCrossButton.click();
            }
        } catch (Exception e) {
            // Element not found or not visible, which is fine for an optional popup
            System.out.println("Initial popup not found or could not be closed: " + e.getMessage());
        }
    }

    public void clickRegisterButtonOnHomePage() {
        registerButtonOnHomePage.click();
    }

    public boolean isRegisterButtonDisplayedOnHomePage() {
        try {
            return registerButtonOnHomePage.isDisplayed() && registerButtonOnHomePage.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isShopNowTextDisplayed() {
        try {
            return shopNowTextElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isMyEbayButtonDisplayed() {
        try {
            return myEbayButton.isDisplayed() && myEbayButton.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickMyEbayButton() {
        myEbayButton.click();
    }

    public void scrollToSettingsButton() {
        try {
            // Scroll to find a TextView containing the text "Settings"
            driver.findElement(io.appium.java_client.AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                + ".scrollIntoView(new UiSelector().className(\"android.widget.TextView\").textContains(\"Settings\").instance(0))"
            ));
        } catch (Exception e) {
            System.err.println("Error scrolling to settings button: " + e.getMessage());
           
        }
    }

    public void clickSettingsButton() {
        settingsButton.click();
    }

    public boolean isSignOutButtonVisible() {
        try {
            return signOutButton.isDisplayed() && signOutButton.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickSignOutButton() {
        signOutButton.click();
    }

    // --- New Methods for Search and Navigation ---

    public void enterSearchTerm(String searchTerm) {
        if (searchBar.isDisplayed()) {
            searchBar.sendKeys(searchTerm);
        } else {
            throw new NoSuchElementException("Search bar not found.");
        }
    }

    public void clickSearchButton() {
        // The actual search submission is now handled by a keyboard event in CheckoutSteps.java
        // If there were a separate visible search icon *on the page* to click after typing,
        // you would declare it as a WebElement (e.g., searchIconOnPage) and click it here.
        System.out.println("clickSearchButton() in HomePage called. Actual submission is expected via keyboard event.");
    }

    public void searchForProduct(String searchTerm) {
        enterSearchTerm(searchTerm);
        // Removed call to clickSearchButton() as submission is handled by a separate step
    }

    public void navigateToShoppingCart() {
        if (shoppingCartLink.isDisplayed()) {
            shoppingCartLink.click();
        } else {
            throw new NoSuchElementException("Shopping cart link not found.");
        }
    }

    public void navigateToOrderHistory() {
        if (myEbayMenuButton.isDisplayed()) { 
            myEbayMenuButton.click();
            
            try {
                // Wait for orderHistoryLink to be clickable if necessary
                if (orderHistoryLink.isDisplayed()) {
                    orderHistoryLink.click();
                } else {
                    throw new NoSuchElementException("Order history link not found after clicking My eBay.");
                }
            } catch (NoSuchElementException e) {
                throw e; 
            }
        } else {
            throw new NoSuchElementException("My eBay menu button not found for navigating to order history.");
        }
    }
    // --- End of New Methods ---

    // --- Methods for Category Navigation (New) ---
    public void clickCategoriesButton() {
        if (categoriesButton.isDisplayed()) {
            categoriesButton.click();
        } else {
            throw new NoSuchElementException("Categories button not found on Home Page.");
        }
    }
   

  
    public void tapSearchOnHomePage() {
        if (searchBar.isDisplayed()) {
            searchBar.click(); 
        } else {
            throw new NoSuchElementException("Initial Search bar display element not found on Home Page.");
        }
    }

    public void typeIntoActiveSearchField(String searchTerm) {
        if (productTitleElement.isDisplayed() && productTitleElement.isEnabled()) {
            productTitleElement.sendKeys(searchTerm);
        } else {
            throw new NoSuchElementException("Active search input field not found or not enabled after tapping search bar.");
        }
    }

    public void submitSearchFromKeyboard() {
        // This method's original intent might have been to interact with the keyboard.
        // However, the actual key press (AndroidKey.SEARCH or AndroidKey.ENTER)
        // is now handled in the CheckoutSteps.java step definition for "I submit the search from keyboard".
        // This WebElement 'submitSearchFromKeyboard' with accessibility ID "Search" likely refers to the keyboard's search key.
        // Interacting with it directly via .click() here is usually not the standard way; pressKey is preferred.
        System.out.println("HomePage.submitSearchFromKeyboard() called. Actual keyboard key press is in step definition.");
        // If you intend to use this element for a different purpose, its usage and locator should be reviewed.
    }
}