package com.example.stepdefinitions;

import com.example.pages.EbayFlowPage;
import com.example.pages.HomePage;
import com.example.utils.AppiumDriverConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import static org.testng.Assert.*;

public class CheckoutSteps {

    private AppiumDriver driver;
    private HomePage homePage;
    private EbayFlowPage ebayFlowPage;
    private String currentProductName; 

    @Before // General hook for scenarios in this class
    public void setUp() {
        driver = AppiumDriverConfig.getDriver();
        homePage = new HomePage(driver);
        ebayFlowPage = new EbayFlowPage(driver);
      
    }

  
    @Given("I am a logged-in user")
    public void i_am_a_logged_in_user() {
        System.out.println("Assumption: User is already logged in.");
        assertTrue(homePage.isMyEbayButtonDisplayed(), "User does not appear to be logged in (My eBay button not found).");
    }

    @When("I search for {string}")
    public void i_search_for(String productName) {
        this.currentProductName = productName;
        homePage.searchForProduct(productName);
    }

    @Then("I should see a list of products related to {string}")
    public void i_should_see_a_list_of_products_related_to(String productName) {
        assertTrue(ebayFlowPage.areProductListResultsDisplayed(), "Product list for '" + productName + "' was not displayed.");
    }

    @When("I select the first product from the list")
    public void i_select_the_first_product_from_the_list() {
        ebayFlowPage.selectFirstProductFromList();
    }

    @Then("I should see the product details page for the selected {string}")
    public void i_should_see_the_product_details_page_for_the_selected(String productName) {
        assertTrue(ebayFlowPage.isProductDetailPageDisplayed(productName), "Product detail page for '" + productName + "' was not displayed correctly.");
    }

    @And("I should see an {string} button")
    public void i_should_see_an_button(String buttonText) {
        if (buttonText.equalsIgnoreCase("Add to cart")) {
            assertTrue(ebayFlowPage.isAddToCartButtonDisplayed(), "'Add to cart' button was not displayed.");
        } else if (buttonText.equalsIgnoreCase("Proceed to checkout")) {
            assertTrue(ebayFlowPage.isProceedToCheckoutButtonDisplayed(), "'Proceed to checkout' button was not displayed.");
        }
    }

    @Given("I am on the product details page for a {string}")
    public void i_am_on_the_product_details_page_for_a(String productName) {
        this.currentProductName = productName; // Ensure current product is set if scenario starts here
        assertTrue(ebayFlowPage.isProductDetailPageDisplayed(productName), "Not on the product detail page for '" + productName + "'.");
    }

    @When("I click the {string} button")
    public void i_click_the_button(String buttonText) {
        if (buttonText.equalsIgnoreCase("Add to cart")) {
            ebayFlowPage.clickAddToCartButton();
        } else if (buttonText.equalsIgnoreCase("Proceed to checkout")) {
            ebayFlowPage.clickProceedToCheckoutButton();
        } else {
            fail("Button text '" + buttonText + "' not recognized in this step.");
        }
    }

    @Then("the item {string} should be added to my shopping cart")
    public void the_item_should_be_added_to_my_shopping_cart(String productName) {
    }

    @And("I should see a confirmation that the item was added")
    public void i_should_see_a_confirmation_that_the_item_was_added() {
        assertTrue(ebayFlowPage.isItemAddedConfirmationDisplayed(), "Item added to cart confirmation was not displayed.");
    }

    @When("I navigate to the shopping cart")
    public void i_navigate_to_the_shopping_cart() {
        homePage.navigateToShoppingCart();
    }

    @Then("I should see {string} in my shopping cart")
    public void i_should_see_in_my_shopping_cart(String productName) {
        assertTrue(ebayFlowPage.isItemInCart(productName), "'" + productName + "' was not found in the shopping cart.");
    }

    @Given("my shopping cart contains a {string}")
    public void my_shopping_cart_contains_a(String productName) {
        this.currentProductName = productName; 
        assertTrue(ebayFlowPage.isItemInCart(productName), "Precondition failed: Shopping cart does not contain '" + productName + "'.");
    }

    @And("I am on the shopping cart page")
    public void i_am_on_the_shopping_cart_page() {
        assertTrue(ebayFlowPage.isProceedToCheckoutButtonDisplayed(), "Not on the shopping cart page (Proceed to checkout button not found).");
    }

    @Then("I should be on the checkout page")
    public void i_should_be_on_the_checkout_page() {
        assertTrue(ebayFlowPage.isCheckoutPageDisplayed(), "Not on the checkout page.");
    }

    @And("I should see order summary for {string}")
    public void i_should_see_order_summary_for(String productName) {
        assertTrue(ebayFlowPage.isOrderSummaryDisplayedFor(productName), "Order summary for '" + productName + "' was not displayed.");
    }

    @And("I should see shipping information section")
    public void i_should_see_shipping_information_section() {
        assertTrue(ebayFlowPage.isShippingInformationSectionDisplayed(), "Shipping information section was not displayed.");
    }

    @And("I should see payment information section")
    public void i_should_see_payment_information_section() {
        assertTrue(ebayFlowPage.isPaymentInformationSectionDisplayed(), "Payment information section was not displayed.");
    }

    @When("I confirm my order details on the checkout page (simplified)")
    public void i_confirm_my_order_details_on_the_checkout_page_simplified() {
        ebayFlowPage.confirmOrderDetailsOnCheckoutPage();
    }

    @Then("I should see an order confirmation message")
    public void i_should_see_an_order_confirmation_message() {
        assertTrue(ebayFlowPage.isOrderConfirmationMessageDisplayed(), "Order confirmation message was not displayed.");
        
    }

    @Given("I have successfully placed an order for a {string}")
    public void i_have_successfully_placed_an_order_for_a(String productName) {
        this.currentProductName = productName;
        System.out.println("Assumption: Order for '" + productName + "' was successfully placed.");    }

    @When("I navigate to my order history")
    public void i_navigate_to_my_order_history() {
        homePage.navigateToOrderHistory();
    }

    @Then("I should see the order for {string} in my order history")
    public void i_should_see_the_order_for_in_my_order_history(String productName) {
        assertTrue(ebayFlowPage.isOrderInHistory(productName), "Order for '" + productName + "' was not found in order history.");
    }

    @And("the order status should be {string} or {string}")
    public void the_order_status_should_be_or(String status1, String status2) {
        String actualStatus = ebayFlowPage.getOrderStatusFromHistory(currentProductName);
        assertTrue(actualStatus.equalsIgnoreCase(status1) || actualStatus.equalsIgnoreCase(status2),
                "Order status for '" + currentProductName + "' was '" + actualStatus + "', expected '" + status1 + "' or '" + status2 + "'.");
    }

    @After // General hook for scenarios in this class
    public void tearDown() {
        System.out.println("CheckoutSteps @After hook executed.");
    }

    // --- New Step Definitions for Category Navigation and Specific Product Cart Addition ---

    @When("I click on categories")
    public void i_click_on_categories() {
        homePage.clickCategoriesButton();
    }

    @When("I click on Books & Magazines")
    public void i_click_on_books_magazines() {
        ebayFlowPage.clickBooksAndMagazinesCategory();
    }

    @When("I click on Books")
    public void i_click_on_books() {
        ebayFlowPage.clickBooksSubCategory();
    }

    @When("I click on the product named {string}")
    public void i_click_on_the_product_named(String productName) {
        this.currentProductName = productName; // Store for later assertions
        ebayFlowPage.selectProductByName(productName);
    }

    @When("I scroll to and click the Add to cart button")
    public void i_scroll_to_and_click_the_add_to_cart_button() {
        ebayFlowPage.scrollToAndClickAddToCart();
    }

    @Then("I click on back button")
    public void i_click_on_back_button() {
        driver.navigate().back();
    }

    // --- Step Definitions for Watch Flow Scenario ---
    @When("I tap on the search bar on the home page")
    public void i_tap_on_the_search_bar_on_the_home_page() {
        homePage.tapSearchOnHomePage();
    }

    @And("the user searches for {string}")
    public void the_user_searches_for(String searchTerm) {
        homePage.typeIntoActiveSearchField(searchTerm);
        // Assuming this method just types, not submits.
    }

    @And("I submit the search from keyboard")
    public void i_submit_the_search_from_keyboard() {
        System.out.println("Attempting to submit search by pressing SEARCH key on keyboard.");
        try {
            if (driver instanceof AndroidDriver) {
                 ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.SEARCH));
                 System.out.println("Successfully pressed SEARCH key.");
            } else {
                fail("Cannot submit search from keyboard: Driver is not an AndroidDriver.");
            }
        } catch (Exception e) {
            System.err.println("Error submitting search from keyboard via SEARCH key: " + e.getMessage());
            e.printStackTrace();
            fail("Failed to submit search from keyboard: " + e.getMessage());
        }
    }

    @Then("I should see search results displayed")
    public void i_should_see_search_results_displayed() {
        assertTrue(ebayFlowPage.areSearchResultsDisplayed(), "Search results were not displayed.");
    }

    @Then("I should see search results containing {string}")
    public void i_should_see_search_results_containing(String expectedTextInTitle) {
        assertTrue(ebayFlowPage.verifySearchResultsContainText(expectedTextInTitle),
                "Search results page did not contain any item with text: '" + expectedTextInTitle + "'");
    }

    @When("I select the product matching text {string} from the search results")
    public void i_select_the_product_matching_text_from_the_search_results(String productName) {
        this.currentProductName = productName; // Store for potential later use
        ebayFlowPage.selectProductByName(productName);
    }
    
    @Then("I should be on the product detail page for {string}")
    public void i_should_be_on_the_product_detail_page_for(String expectedProductName) {
        assertTrue(ebayFlowPage.isProductDetailPageDisplayed(expectedProductName),
                "Not on the product detail page for '" + expectedProductName + "', or product title does not match.");
    }

    @When("I tap to select product color option")
    public void i_tap_to_select_product_color_option() {
        ebayFlowPage.tapProductColorSelector();
    }

    @When("I select the color {string}")
    public void i_select_the_color(String colorName) {
        ebayFlowPage.selectColorOption(colorName);
    }

    @When("I apply the color selection for the watch")
    public void i_apply_the_color_selection_for_the_watch() {
        ebayFlowPage.clickAddToCartButtonInOptionsDialog();
    }

    @When("I tap the add to cart button on the product detail page")
    public void i_tap_the_add_to_cart_button_on_the_product_detail_page() {
        System.out.println("Attempting to click the main 'Add to cart' button on the product detail page.");
        ebayFlowPage.clickAddToCartButton(); 
    }

    @Then("I should see a confirmation that the item was added to cart")
    public void i_should_see_a_confirmation_that_the_item_was_added_to_cart() {
        assertTrue(ebayFlowPage.isItemAddedConfirmationDisplayed(),
                "Confirmation dialog/message that the item was added to cart was not displayed.");
    }

    @When("I navigate to the shopping cart from the item added confirmation")
    public void i_navigate_to_the_shopping_cart_from_the_item_added_confirmation() {
        ebayFlowPage.clickGoToCartButtonOnConfirmation();
    }

    @Then("I should see {string} with color {string} in my shopping cart")
    public void i_should_see_with_color_in_my_shopping_cart(String productName, String expectedColor) {
        assertTrue(ebayFlowPage.isItemInCartWithColor(productName, expectedColor),
                "Item '" + productName + "' with color '" + expectedColor + "' not found in shopping cart, or details do not match.");
    }

    @And("I should see the proceed to checkout button")
    public void i_should_see_the_proceed_to_checkout_button() {
        assertTrue(ebayFlowPage.isProceedToCheckoutButtonDisplayed(),
                "'Proceed to checkout' (or 'Go to checkout') button was not displayed on the shopping cart page.");
    }


} 