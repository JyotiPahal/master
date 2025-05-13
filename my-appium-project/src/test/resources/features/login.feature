Feature: Application Login
  As a user
  I want to log in to the application
  So that I can access my account

  Scenario: Negative to Signin attempt for new user
    When I launch app 
    And I close any initial popup by clicking the cross button
    Then I wait for 10 seconds
    Then I verify the ebay text to confirm the home page of app 
     Then I wait for 10 seconds
    And I verify the signin text is displayed on home page
     Then I wait for 10 seconds
    And I click on the home page signin button
     Then I wait for 10 seconds
    Then I verify the welcome text is displayed on signin page
    And I verify the username text box is clickable on signin page
    When I click on username text box and enter "Poonam"
    And I verify the password text box is clickable on signin page
    When I enter password "abc8930415192" in password text box
    And I verify the signin button is clickable on signin page
    And I click on the signin page signin button on the signin page 
    Then I verify the error message "Oops, that's not a match" is displayed 
    And I click on the cross button on the signin page 

  # Scenario: Verify the register functionality for user
  #   When I launch app
  #   Then I check for ebay logo on home page
  #   And I verify the register button is displayed on home page
  #   When I click on the register button on home page
  #   Then I verify the "Create an account" text is displayed on register page
  #   And I verify the email text box is clickable on register page
  #   When I click on email text box and enter "poonamyadav1234@gmail.com" in email text box on register page
  #   And I verify the first name text box is clickable on register page
  #   When I click first name text box and enter "Poonam" in first name text box on register page
  #   And I verify the last name text box is clickable on register page
  #   When I click on last name text box and enter "Yadav" in last name text box on register page
  #   Then I verify the continue button is visible and clickable on register page
  #   And I click on the continue button on register page
  #   Then I verify I am on the create password page
  #   And I verify the password text box is clickable on create password page
  #   When I click on password text box and enter "abc8930415192" in password text box on create password page
  #   Then I verify the password complexity text is displayed on create password page
  #   And I verify the legal text is displayed on create password page
  #   And I verify the user agreement text is displayed on create password page
  #   And I verify the privacy notice text is displayed on create password page
  #   And I verify the create account button is clickable on create password page
  #   When I click on the create account button on create password page
  #   Then I verify the "Add your phone number" title is displayed
  #   And I verify the description text is displayed on add phone number page
  #   And I verify the phone number edit box is clickable on add phone number page
  #   When I click on phone number edit box and enter "8930414192" on add phone number page
  #   Then I verify the continue button is clickable on add phone number page
  #   When I click on the continue button on add phone number page
  #   Then I verify the "Enter verification code" text is displayed on add phone number page
  #   And I verify the continue button is clickable on enter verification code page
  #   When I click on the continue button on enter verification code page

    Scenario: Verify Signin with valid credentials and signout for same user
    When I launch app
    And I close any initial popup by clicking the cross button
    Then I wait for 10 seconds
    Then I verify the ebay text to confirm the home page of app 
     Then I wait for 10 seconds
    And I verify the signin text is displayed on home page
     Then I wait for 10 seconds
    And I click on the home page signin button
     Then I wait for 10 seconds
    Then I verify the welcome text is displayed on signin page
    And I verify the username text box is clickable on signin page
    When I click on username text box and enter "poonamyadav@gmail.com"
    And I verify the password text box is clickable on signin page
    When I enter password "test12345" in password text box
    And I verify the signin button is clickable on signin page
    And I click on the signin page signin button on the signin page 
    And I verify the simplyfy your sign in text is displayed on signin page
    Then I verify the skip for now button is clickable on signin page
    When I click on the skip for now button on the signin page
    Then I verify the shopnow text is displayed on home page
    # And I verify the myebay button is displayed on home page
    # When I click on the myebay button on home page
    # And I scroll to the settings button on myebay page
    # And I click on the settings button on myebay page
    # Then I verify the sign out button is visible on settings page
    # When I click on the sign out button on settings page
    # Then I verify the register button is displayed on home page 

 