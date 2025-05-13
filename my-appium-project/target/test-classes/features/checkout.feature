Feature: Product Purchase Flow
  As a registered user
  I want to be able to search for products, add them to my cart, and proceed to checkout
  So that I can purchase items from the eBay application.

  Background: User is logged in
    Given I am a logged-in user

  # Scenario: Search for a product and view product details
  #   When I search for "laptop"
  #   Then I should see a list of products related to "laptop"
  #   When I select the first product from the list
  #   Then I should see the product details page for the selected "laptop"
  #   And I should see an "Add to cart" button

  # Scenario: Add product to shopping cart and view cart
  #   Given I am on the product details page for a "laptop"
  #   When I click the "Add to cart" button
  #   Then the item "laptop" should be added to my shopping cart
  #   And I should see a confirmation that the item was added
  #   When I navigate to the shopping cart
  #   Then I should see "laptop" in my shopping cart
  #   And I should see a "Proceed to checkout" button

  # Scenario: Proceed to checkout (simplified)
  #   Given my shopping cart contains a "laptop"
  #   And I am on the shopping cart page
  #   When I click the "Proceed to checkout" button
  #   Then I should be on the checkout page
  #   And I should see order summary for "laptop"
  #   And I should see shipping information section
  #   And I should see payment information section
  #   # For automation, we might not proceed with actual payment
  #   When I confirm my order details on the checkout page (simplified)
  #   Then I should see an order confirmation message

  # Scenario: View order in order history (simplified)
  #   Given I have successfully placed an order for a "laptop"
  #   When I navigate to my order history
  #   Then I should see the order for "laptop" in my order history
  #   And the order status should be "Processing" or "Confirmed"

  # Scenario: Navigate categories and add specific book to cart
  #   Given I am a logged-in user
  #   When I click on categories
  #   Then I click on back button 
  #   And I click on Books & Magazines 
  #   And I click on Books 
  #   And I click on the product named "2 book lot spark of the everflame glow kindred's"
  #   Then I should see the product details page for the selected "2 book lot spark of the everflame glow kindred's"
  #   When I scroll to and click the Add to cart button
  #   Then the item "2 book lot spark of the everflame glow kindred's" should be added to my shopping cart
  #   And I should see a confirmation that the item was added
  #   # Optional: Add steps to navigate to cart and verify item presence if needed for this flow
  #   # When I navigate to the shopping cart
  #   # Then I should see "2 book lot spark of the everflame glow kindred's" in my shopping cart


  @SearchFlow
  Scenario: Search, select Casio watch, add to cart, and verify cart (Watch Flow)
    Given I am a logged-in user 
    When I tap on the search bar on the home page
    And the user searches for "women watch"
    And I submit the search from keyboard 
    Then I should see search results displayed
    And I wait for 10 seconds 
    When I select the product matching text "Casio Women's LTP-V007 Waterproof Stainless Steel/Leather Watch" from the search results
    And I wait for 10 seconds 
    Then I should be on the product detail page for "Casio Women's LTP-V007 Waterproof Stainless Steel/Leather Watch"
    When I scroll to and click the Add to cart button
    Then I should see a confirmation that the item was added to cart
    When I navigate to the shopping cart from the item added confirmation
    Then I should see "Casio Women's LTP-V007 Waterproof Stainless Steel/Leather Watch" in my shopping cart
    And I should see the proceed to checkout button 