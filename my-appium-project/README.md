# eBay Android App Automation Project

## 1. Project Overview

This project aims to automate the testing of the eBay Android application using a Behavior-Driven Development (BDD) approach. It focuses on user flows such as searching for products, adding items to the cart, and navigating through different sections of the app. The framework is built using Java with Appium for mobile automation, TestNG as the test runner, Cucumber for BDD, and Maven for dependency management and build automation. It utilizes the Page Object Model (POM) design pattern for creating maintainable and scalable test scripts.

## 2. Tech Stack

The following technologies and libraries are used in this project:

*   **Programming Language:** Java (JDK 11 or higher recommended)
*   **Mobile Automation Tool:** Appium (version 2.x with UiAutomator2 driver for Android)
*   **BDD Framework:** Cucumber (for writing Gherkin feature files and Java step definitions)
*   **Test Runner:** TestNG (for managing and executing test scenarios)
*   **Build & Dependency Management:** Apache Maven
*   **Appium Java Client:** `io.appium:java-client` (version 9.2.2) - For Appium server interaction.
*   **Selenium Java:** `org.seleniumhq.selenium:selenium-java` (version 4.19.1) - Core WebDriver APIs and utilities.
*   **Cucumber JVM:**
    *   `io.cucumber:cucumber-java` (version 7.14.0) - For Java step definitions.
    *   `io.cucumber:cucumber-testng` (version 7.14.0) - For TestNG integration with Cucumber.
*   **Assertion Library:** AssertJ (version 3.24.2) - For fluent assertions (though TestNG assertions are primarily used in current step definitions).
*   **Logging:**
    *   SLF4J API (`org.slf4j:slf4j-api`)
    *   Logback Classic (`ch.qos.logback:logback-classic`) - For logging implementation.
*   **Design Pattern:** Page Object Model (POM)

## 3. Project Structure

The project follows a standard Maven directory structure with specific organization for BDD components:

```
my-appium-project/
├── pom.xml                 # Maven project configuration, dependencies, plugins
├── README.md               # This file
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/example/
│   │           ├── pages/              # Page Object classes (e.g., LoginPage.java, HomePage.java, EbayFlowPage.java)
│   │           └── utils/              # Utility classes (e.g., AppiumDriverConfig.java, PropertyReader.java)
│   └── test/
│       ├── java/
│       │   └── com/example/
│       │       ├── runners/            # TestNG/Cucumber runner classes (e.g., TestRunner.java)
│       │       └── stepdefinitions/    # Step definition classes (e.g., LoginSteps.java, CheckoutSteps.java)
│       └── resources/
│           ├── config/             # Configuration files (e.g., android.properties)
│           ├── features/           # Cucumber feature files (e.g., login.feature, checkout.feature)
│           └── logback.xml         # Logging configuration
└── target/                   # Maven build output (compiled classes, reports)
```

### Key Components:

*   **`pom.xml`**: Defines project dependencies (Appium, Selenium, Cucumber, TestNG, etc.) and Maven plugins (Surefire for running tests, Compiler).
*   **Page Objects (`src/main/java/com/example/pages`)**:
    *   Each class represents a page or a significant component of the eBay application's UI.
    *   Contains WebElement locators (using `@AndroidFindBy`) and methods that encapsulate interactions with those elements (e.g., `enterUsername()`, `clickAddToCartButton()`).
    *   Promotes code reusability and maintainability by separating UI interaction logic from test logic.
*   **Utilities (`src/main/java/com/example/utils`)**:
    *   `AppiumDriverConfig.java`: Manages the Appium driver lifecycle (initialization with desired capabilities, and quitting). It reads capabilities from `android.properties`.
    *   `PropertyReader.java`: A utility to read key-value pairs from `.properties` files, used here for Appium capabilities.
*   **Feature Files (`src/test/resources/features`)**:
    *   Written in Gherkin syntax (`.feature` extension).
    *   Describe application features and scenarios in a human-readable format (Given-When-Then).
    *   Example: `checkout.feature` defines scenarios for product purchase flows.
*   **Step Definitions (`src/test/java/com/example/stepdefinitions`)**:
    *   Java classes that implement the Gherkin steps defined in the feature files.
    *   Each Gherkin step (e.g., `When I search for "laptop"`) maps to a Java method annotated with `@When`, `@Given`, `@Then`, etc.
    *   These methods use Page Object classes to interact with the application.
    *   Example: `CheckoutSteps.java` contains methods for the checkout flow.
*   **Test Runner (`src/test/java/com/example/runners`)**:
    *   `TestRunner.java`: A TestNG class annotated with `@CucumberOptions`.
    *   Specifies the location of feature files and step definitions.
    *   Configures Cucumber plugins for reporting (e.g., HTML reports, JSON reports).
    *   Allows running Cucumber scenarios as TestNG tests.
*   **Configuration (`src/test/resources/config`)**:
    *   `android.properties`: Stores Appium server capabilities (device name, platform version, app package, app activity, server URL) to keep them separate from code.
*   **Logging (`src/test/resources/logback.xml`)**:
    *   Configures how log messages are formatted and where they are outputted (e.g., console, file).

## 4. How It Works (Interconnections)

1.  **Test Execution Start**:
    *   Maven (`mvn test`) triggers the Surefire plugin.
    *   Surefire executes the `TestRunner.java` class because it's a TestNG test.

2.  **Cucumber Initialization**:
    *   `TestRunner.java` (via `@CucumberOptions`) tells Cucumber where to find feature files (`features = "src/test/resources/features"`) and step definition glue code (`glue = "com.example.stepdefinitions"`).

3.  **Scenario Execution**:
    *   Cucumber parses a feature file (e.g., `checkout.feature`).
    *   For each Gherkin step in a scenario:
        *   Cucumber finds the matching Java method in a step definition class (e.g., a `@When` annotated method in `CheckoutSteps.java`).

4.  **Driver Initialization & Management**:
    *   Before a scenario (or as needed, often managed by hooks like `@Before` in step definition classes or a base test class), `AppiumDriverConfig.getDriver()` is called.
    *   `AppiumDriverConfig` reads device and app capabilities from `android.properties` (using `PropertyReader`).
    *   It initializes an `AndroidDriver` instance, connecting to the Appium server (specified by `appiumServerUrl` in properties).
    *   This driver instance is then used by Page Objects.

5.  **Step Definition Logic & Page Object Interaction**:
    *   Inside a step definition method (e.g., `i_search_for(String productName)` in `CheckoutSteps.java`):
        *   It typically instantiates or uses an existing instance of a relevant Page Object (e.g., `HomePage homePage = new HomePage(driver);`).
        *   It calls methods on the Page Object (e.g., `homePage.searchForProduct(productName);`).

6.  **Page Object Actions**:
    *   The Page Object method (e.g., `searchForProduct` in `HomePage.java`) contains the logic to interact with WebElements on that specific page.
    *   It uses `AppiumFieldDecorator` (in the constructor) to initialize WebElements defined with `@AndroidFindBy` (e.g., `usernameField.sendKeys(username);`).
    *   These interactions send commands to the Appium server, which then translates them into actions on the Android device/emulator via UiAutomator2.

7.  **Assertions**:
    *   `@Then` steps in step definitions perform assertions to verify expected outcomes (e.g., `assertTrue(ebayFlowPage.isProductDetailPageDisplayed(productName));`).

8.  **Driver Teardown**:
    *   After scenarios or tests complete (often managed by `@After` hooks), `AppiumDriverConfig.quitDriver()` should be called to close the app and terminate the Appium session gracefully.

9.  **Reporting**:
    *   Cucumber plugins configured in `TestRunner.java` generate test execution reports (e.g., HTML, JSON) in the `target/cucumber-reports/` directory.

This flow ensures a separation of concerns:
*   **Feature files:** Define *what* the system should do.
*   **Step definitions:** Define *how* to test those behaviors by orchestrating Page Object interactions.
*   **Page Objects:** Define *where* and *how* to interact with specific UI elements.
*   **Utilities:** Provide common services like driver management and configuration reading.

This structure makes the test suite more readable, maintainable, and scalable. 