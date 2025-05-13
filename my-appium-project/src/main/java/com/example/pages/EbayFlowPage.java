package com.example.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.NoSuchElementException; // Added based on HomePage.java
import io.appium.java_client.AppiumBy; // For dynamic locators if needed
import org.openqa.selenium.By;

public class EbayFlowPage {

    private AppiumDriver driver;

    // --- Product Listing Page Elements ---
    @AndroidFindBy(id = "com.ebay.mobile:id/recycler_view_item") // list item container for products
    private List<WebElement> productListResults;

    @AndroidFindBy(id = "com.ebay.mobile:id/title") // title on product list item
    private WebElement productListItemTitle; 

    // --- Product Detail Page Elements ---
    @AndroidFindBy(id = "com.ebay.mobile:id/button_add_to_cart") // Placeholder
    private WebElement addToCartButton;

    @AndroidFindBy(id = "com.ebay.mobile:id/textview_item_name") 
    private WebElement productTitleOnDetailPage;

    // --- Shopping Cart Page Elements ---
    @AndroidFindBy(id = "com.ebay.mobile:id/cart_item_title") // Placeholder for item titles in cart
    private List<WebElement> cartItemTitles;

    // Placeholder for the list of individual cart item container elements
    @AndroidFindBy(id = "com.ebay.mobile:id/cart_item_container") 
    private List<WebElement> cartItemContainers;

    @AndroidFindBy(id = "com.ebay.mobile:id/button_proceed_to_checkout") 
    private WebElement proceedToCheckoutButton;

    @AndroidFindBy(id = "com.ebay.mobile:id/cell_collection_item") 
    private WebElement cartMessageText;

    @AndroidFindBy(id = "com.ebay.mobile:id/textview_cart_message") 
    private WebElement productTitleElement;
    
    // --- Checkout Page Elements ---
    @AndroidFindBy(id = "com.ebay.mobile:id/checkout_order_summary") 
    private WebElement orderSummarySection;

    @AndroidFindBy(id = "com.ebay.mobile:id/checkout_shipping_info") 
    private WebElement shippingInfoSection;

    @AndroidFindBy(id = "com.ebay.mobile:id/checkout_payment_info") 
    private WebElement paymentInfoSection;

    @AndroidFindBy(id = "com.ebay.mobile:id/button_confirm_order") 
    private WebElement confirmOrderButton;

    @AndroidFindBy(id = "com.ebay.mobile:id/textview_order_confirmation_message")
    private WebElement orderConfirmationMessageText;


  // Placeholder for order titles in history
    private List<WebElement> orderHistoryItemTitles;

    @AndroidFindBy(id = "com.ebay.mobile:id/order_history_item_status") // Placeholder for order statuses
    private List<WebElement> orderHistoryItemStatuses;

   
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Books & Magazines']") 
    private WebElement booksAndMagazinesCategoryLink;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Books']") 
    private WebElement booksSubCategoryLink;

    // Placeholder for a list of all product titles on a listing page to find a specific one
    @AndroidFindBy(id = "com.ebay.mobile:id/search_suggestion_text") // This might be the same as productListItemTitle, ensure it represents all titles
    private List<WebElement> allProductTitlesOnPage;

    // --- Elements for Watch Flow Scenario (New) ---
    // Placeholder for the "Color: Select" dropdown on Product Detail Page (Image 6)
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Color: Select']") 
    private WebElement productColorSelectorDropdown;

    // Placeholder for the "Add to cart" button within the "Select Options" dialog (Image 8)
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Add to cart']") 
    private WebElement addToCartButtonInOptionsDialog;

    // Placeholder for the "Go to cart" button on the "Added to cart" confirmation (Image 9)
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Go to cart']") 
    private WebElement goToCartButtonOnConfirmation;

    public EbayFlowPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    // --- Product Listing Page Methods ---
    public boolean areProductListResultsDisplayed() {
        try {
            return productListResults != null && !productListResults.isEmpty() && productListResults.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectFirstProductFromList() {
        if (areProductListResultsDisplayed()) {
            productListResults.get(0).click(); // Clicks the first product item
        } else {
            throw new NoSuchElementException("Product list is not displayed or is empty.");
        }
    }

    // --- Product Detail Page Methods ---
    public boolean isProductDetailPageDisplayed(String expectedProductName) {
        try {
            return productTitleOnDetailPage.isDisplayed() && productTitleOnDetailPage.getText().contains(expectedProductName);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddToCartButtonDisplayed() {
        try {
            return addToCartButton.isDisplayed() && addToCartButton.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    // --- Shopping Cart Page Methods ---
    public boolean isItemAddedConfirmationDisplayed() {
        try {
            // This will depend on how the app shows confirmation.
            // It might be a toast, a specific text, or change in cart icon.
            // Using a generic cartMessageText for now.
            return cartMessageText.isDisplayed() && cartMessageText.getText().toLowerCase().contains("added to cart"); // Example text
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isItemInCart(String productName) {
        try {
            for (WebElement itemTitleElement : cartItemTitles) {
                if (itemTitleElement.isDisplayed() && itemTitleElement.getText().contains(productName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProceedToCheckoutButtonDisplayed() {
        try {
            return proceedToCheckoutButton.isDisplayed() && proceedToCheckoutButton.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
    }

    // --- Checkout Page Methods ---
    public boolean isCheckoutPageDisplayed() {
        // Assume one of the key sections' visibility confirms being on the checkout page
        try {
            return orderSummarySection.isDisplayed() || shippingInfoSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOrderSummaryDisplayedFor(String productName) {
        try {
            // This will need more specific locators or logic to find the product in summary
            return orderSummarySection.isDisplayed() && orderSummarySection.getText().contains(productName);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isShippingInformationSectionDisplayed() {
        try {
            return shippingInfoSection.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isPaymentInformationSectionDisplayed() {
        try {
            return paymentInfoSection.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void confirmOrderDetailsOnCheckoutPage() {
        // Simplified - actual confirmation might involve multiple steps
        if (confirmOrderButton.isDisplayed() && confirmOrderButton.isEnabled()) {
            confirmOrderButton.click();
        } else {
            throw new NoSuchElementException("Confirm order button not found or not enabled on checkout page.");
        }
    }

    public String getOrderConfirmationMessage() {
        try {
            return orderConfirmationMessageText.isDisplayed() ? orderConfirmationMessageText.getText() : "";
        } catch (NoSuchElementException e) {
            return "";
        }
    }
    
    public boolean isOrderConfirmationMessageDisplayed() {
        try {
            return orderConfirmationMessageText.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    // --- Order History Page Methods ---
    public boolean isOrderInHistory(String productName) {
        try {
            for (WebElement orderTitleElement : orderHistoryItemTitles) {
                if (orderTitleElement.isDisplayed() && orderTitleElement.getText().contains(productName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String getOrderStatusFromHistory(String productName) {
        try {
            for (int i = 0; i < orderHistoryItemTitles.size(); i++) {
                if (orderHistoryItemTitles.get(i).isDisplayed() && orderHistoryItemTitles.get(i).getText().contains(productName)) {
                    if (i < orderHistoryItemStatuses.size() && orderHistoryItemStatuses.get(i).isDisplayed()) {
                        return orderHistoryItemStatuses.get(i).getText();
                    }
                    break; 
                }
            }
            return "Status not found or product not in history";
        } catch (Exception e) {
            return "Error retrieving status";
        }
    }

    // --- New Methods for Category Navigation and Specific Product Selection ---
    public void clickBooksAndMagazinesCategory() {
        try {
            booksAndMagazinesCategoryLink.click();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("'Books & Magazines' category link not found.");
        }
    }

    public void clickBooksSubCategory() {
        try {
            booksSubCategoryLink.click();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("'Books' sub-category link not found.");
        }
    }

    public void selectProductByName(String productName) {
        boolean found = false;
        for (WebElement productTitleElement : allProductTitlesOnPage) {
            if (productTitleElement.isDisplayed() && productTitleElement.getText().trim().equalsIgnoreCase(productName)) {
                productTitleElement.click();
                found = true;
                break;
            }
        }
        if (!found) {
            throw new NoSuchElementException("Product named '" + productName + "' not found in the list.");
        }
    }

    public void scrollToAndClickAddToCart() {
        try {
            if (isAddToCartButtonDisplayed()) { 
                 clickAddToCartButton(); 
            } else {
                driver.findElement(AppiumBy.androidUIAutomator(
                   "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollForward()"));
                if(isAddToCartButtonDisplayed()){
                    clickAddToCartButton();
                } else {
                    throw new NoSuchElementException("Add to Cart button not found even after attempting to scroll.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while scrolling to and clicking Add to Cart: " + e.getMessage(), e);
        }
    }
   

    // --- Methods for Watch Flow Scenario ---
    public boolean verifySearchResultsContainText(String expectedText) {
        if (allProductTitlesOnPage == null || allProductTitlesOnPage.isEmpty()) {
            System.err.println("verifySearchResultsContainText: allProductTitlesOnPage is null or empty. Check locator.");
            return false;
        }
        for (WebElement titleElement : allProductTitlesOnPage) {
            if (titleElement.isDisplayed() && titleElement.getText().toLowerCase().contains(expectedText.toLowerCase())) {
                return true; 
            }
        }
        System.out.println("No product title found in search results containing: " + expectedText);
        return false;
    }

    public void tapProductColorSelector() {
        if (productColorSelectorDropdown.isDisplayed()) {
            productColorSelectorDropdown.click(); // 
        } else {
            throw new NoSuchElementException("Product color selector dropdown not found on detail page.");
        }
    }

    public void selectColorOption(String colorName) {
        String colorOptionXPath = "//android.widget.TextView[@text='" + colorName + "']"; 
        try {
            WebElement colorElement = driver.findElement(AppiumBy.xpath(colorOptionXPath));
            if (colorElement.isDisplayed()) {
                colorElement.click(); 
            } else {
                throw new NoSuchElementException("Color option '" + colorName + "' not displayed in the selector.");
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Could not find color option '" + colorName + "' using XPath: " + colorOptionXPath, e);
        }
    }

    public void clickAddToCartButtonInOptionsDialog() {
        if (addToCartButtonInOptionsDialog.isDisplayed()) {
            addToCartButtonInOptionsDialog.click(); 
        } else {
            throw new NoSuchElementException("'Add to cart' button not found in the options selection dialog.");
        }
    }

    public void clickGoToCartButtonOnConfirmation() {
        if (goToCartButtonOnConfirmation.isDisplayed()) {
            goToCartButtonOnConfirmation.click(); 
        } else {
            throw new NoSuchElementException("'Go to cart' button not found on the item added confirmation.");
        }
    }

    public boolean isItemInCartWithColor(String productName, String expectedColor) {
        if (cartItemContainers == null || cartItemContainers.isEmpty()) {
            System.err.println("isItemInCartWithColor: No cart item containers found. Check locator for cartItemContainers.");
            return false;
        }

        for (WebElement itemContainer : cartItemContainers) {
            try {
                WebElement titleElement = itemContainer.findElement(AppiumBy.xpath(".//android.widget.TextView[@resource-id='com.ebay.mobile:id/title']")); // Example: find title by a known child ID
                WebElement colorElement = itemContainer.findElement(AppiumBy.xpath(".//android.widget.TextView[contains(@text, 'Gold') or contains(@text, 'Silver') or contains(@text, 'Color:')]")); // Example: find color element

                if (titleElement.isDisplayed() && titleElement.getText().trim().equalsIgnoreCase(productName)) {
                    if (colorElement.isDisplayed() && colorElement.getText().trim().toLowerCase().contains(expectedColor.toLowerCase())) {
                        return true; 
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("Skipping a cart item container as it didn't match expected structure or content for product: " + productName + " with color: " + expectedColor);
            }
        }
        return false; 
    }

    // Placeholder method for the new step
    public boolean areSearchResultsDisplayed() {
        try {
            // You need to define a reliable locator for the search results area.
            // This is just a placeholder using an example ID.
            // A better check might be to see if any productListItems are present, or if a specific container is visible.
            // return searchResultsContainer.isDisplayed(); 
            return !productListResults.isEmpty(); // A basic check: are there any items?
        } catch (NoSuchElementException e) {
            return false;
        }
    }
} 