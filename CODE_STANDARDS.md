# Code Standards & Best Practices Guide

This document outlines the coding standards and patterns for this Serenity BDD + Cucumber test automation framework.

---

## 1. Project Structure

```
src/test/java/
├── stepdefinitions/          # Cucumber step definitions
│   ├── CommonStepDef.java    # Shared reusable steps
│   └── ModuleName/        # Module-specific step defs
├── pages/                  # Page object models
│   ├── CommonPage.java    # Common locators/methods
│   └── ModuleName/      # Module-specific pages
└── utils/                 # Utility classes
    ├── ExcelReader.java   # Excel data readers
    ├── JsonReader.java   # JSON data readers
    └── Utilities.java   # Helper utilities

src/test/resources/
├── features/              # Gherkin feature files
│   └── ModuleName/
├── testData/             # Test data files
│   ├── *.xlsx          # Excel credential files
│   └── json/            # JSON test data
└── serenity.conf         # Environment configs
```

---

## 2. Page Object Pattern

### 2.1 Naming Conventions

- **Class name**: `PascalCase` (e.g., `DemoASPAwesomePage`)
- **Method/Locator name**: `UPPERCASE` with underscores (e.g., `TXT_SEARCH_PERSON()`)
- **Package**: Match directory structure (e.g., `pages.DemoASPNETAwesome`)

### 2.2 Page Object Inheritance

```java
// For common/shared pages
public class CommonPage extends UIInteractions { }

// For module-specific pages with common base
public class DemoASPAwesomePage extends CommonProDinnerPage { }
```

### 2.3 Locator Method Pattern

```java
// ✅ CORRECT - UPPERCASE method name
public WebElementFacade SEARCH_PERSON_TXTBOX() {
    return $(PARENT_HEADER + "/following::*[@id='txtPerson-awed']");
}

// ❌ WRONG - lowercase
public WebElementFacade searchPersonTextbox() {
    return $(PARENT_HEADER + "/following::*[@id='txtPerson-awed']");
}
```

### 2.4 Return Types

- Use `WebElementFacade` for single elements
- Use `ListOfWebElementFacades` for multiple elements

```java
public WebElementFacade TXT_SEARCH_PERSON() { ... }
public ListOfWebElementFacades GRID_ROWS() { return findAll(By.xpath(selector)); }
```

### 2.5 Dynamic Locators with Parameters

```java
// Using String.format() for dynamic locators
public WebElementFacade LBL_FIELD(String name) {
    return $("//*[contains(text(),'" + name + "')]");
}

public WebElementFacade NAV_NAME(String name) {
    return $(PARENT_HEADER + "//a[text()='" + name + "']");
}
```

---

## 3. Step Definition Pattern

### 3.1 Class Structure

```java
public class GridStepDef {

    @Steps
    CommonStepDef commonStepDef;  // Inject shared steps
    
    @Page
    DemoASPAwesomePage demoPage;  // Inject page object
    
    public static Logger logger = LoggerFactory.getLogger(...);
}
```

### 3.2 Annotations

| Annotation | Purpose |
|------------|---------|
| `@Steps` | Inject CommonStepDef for shared methods |
| `@Page` | Inject page object |
| `@Given` | Gherkin "Given" step |
| `@When` | Gherkin "When" step |
| `@Then` | Gherkin "Then" step |
| `@And` | Gherkin "And" step |
| `@Before` | Cucumber hook before scenario |
| `@After` | Cucumber hook after scenario |

### 3.3 Step Annotation Pattern

```java
@Given("Tester is navigating in {}")
public void navigatePage(String page) {
    this.thePage(page);
}

@Then("I verify the grid filter using parent control element")
public void verifyGridFilteringParentControl() {
    logger.info("Verify Grid Filtering Parent Control");
    // implementation
}
```

### 3.4 DataTable Usage

```java
@And("^I filter the grid using the following :$")
public void filterGridFilteringParentControl(DataTable dataTable) {
    logger.info("Filtering grid using parent control elements with the following DataTable:");
    
    List<Map<String, String>> dataTableList = dataTable.asMaps(String.class, String.class);
    
    for (Map<String, String> e : dataTableList) {
        String field = e.get("field");
        String value = e.get("value");
        // process
    }
}
```

### 3.5 Reusable Methods from CommonStepDef

```java
// Available in all step definitions via @Steps CommonStepDef:
commonStepDef.navigatePage(page);                    // Navigate to page
commonStepDef.clickElement(element);               // Click element  
commonStepDef.enterText(element, value, waitMs);   // Type text
commonStepDef.verifyVisibilityofElement(element); // Assert visible
commonStepDef.waitForPageInSecond(ms);           // Wait
commonStepDef.testStep(message);                 // Log message
commonStepDef.dismissCookieBanner();             // Handle cookies
```

---

## 4. Feature File Pattern

### 4.1 Tags

```gherkin
@UISmoke @Regression @ModuleName
Feature: Feature description
```

### 4.2 Scenario Outline with Examples

```gherkin
@Tag
Scenario Outline: Validate and Verify the Filter grid
  Given Tester is navigating in <Page>
  And I filter the grid using the following :
    | field   | value       |
    | person  | Tracy       |
  Then I validate the grid result using the following :
    | Id   | Person | Food   |
    | 3509 | Tracy  | Banana |

  Examples:
    | Page           |
    | DEMOASPAWESOME |
```

### 4.3 Running Tests by Tag

```bash
# Run specific tag
mvn clean verify -D"cucumber.filter.tags=@UISmoke"

# Run multiple tags (AND)
mvn clean verify -D"cucumber.filter.tags=@Regression and not @Slow"
```

---

## 5. Data-Driven Testing

### 5.1 Excel Credentials (Login)

Excel file must have columns: `role`, `username`, `password`

```java
// Using ExcelReader utility
ExcelReader reader = new ExcelReader();
String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/userCredentials.xlsx";
Map<String, String> credentials = reader.getUsernameAndPasswordByRole(filePath, "UserList", "Admin");
String username = credentials.get("username");
String password = credentials.get("password");
```

### 5.2 JSON Data

```java
// Using JsonReader utility
JsonReader jsonReader = new JsonReader();
List<Map<String, Object>> expectedResults = jsonReader.getGridExpectedResults(filePath, filters);
```

---

## 6. Common Utilities

### 6.1 ExcelReader

| Method | Purpose |
|--------|---------|
| `getData(filePath, sheetName)` | Get all rows as List |
| `getUsernameAndPasswordByRole(filePath, sheet, role)` | Get credentials by role |

### 6.2 JsonReader

| Method | Purpose |
|--------|---------|
| `readJsonFile(filePath)` | Read JSON file |
| `getGridExpectedResults(filePath, filters)` | Get filtered results |

### 6.3 Utilities

| Method | Purpose |
|--------|---------|
| `generateRandomStringInteger(length)` | Generate random string + int |
| `generateRandomString()` | Generate UUID |
| `generateRandomInteger(min, max)` | Generate random int |

---

## 7. Best Practices

### 7.1 Parallel Testing

- **Avoid shared mutable state** between step definitions
- Tests run with 10-way parallelism
- Use `@Before` hooks to reset state if needed

### 7.2 Element Interaction

```java
// ✅ RECOMMENDED - with visibility check and JS fallback
public void clickElement(WebElementFacade element) {
    verifyVisibilityofElement(element);
    try {
        element.click();
    } catch (ElementClickInterceptedException e) {
        // Fallback to JavaScript click
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element.getWrappedElement());
    }
}
```

### 7.3 Waits

- Use `waitABit(milliseconds)` for simple waits
- Use `element.waitUntilVisible().withTimeoutOf(seconds, TimeUnit)` for dynamic waits

### 7.4 Assertions

```java
// ✅ Use Serenity assertions
shouldBeVisible(element);
ensure.that(element).isVisible();

// ✅ Use AssertJ
assertThat(actual).isEqualTo(expected);
assertThat(list).contains(expectedValue);
```

---

## 8. Configuration

### 8.1 serenity.conf

- Page URLs: `pages.<name> = "url"`
- Browser settings per environment
- Chrome-only grid support

### 8.2 Running Tests

```bash
# Local with Chrome
mvn clean verify -D"cucumber.filter.tags=@Tag"

# With environment
mvn clean verify -Denvironment=firefox -D"cucumber.filter.tags=@Tag"

# Headless (for CI)
mvn clean verify -Dheadless.mode=true -D"cucumber.filter.tags=@Tag"
```

---

## 9. Code Review Checklist

- [ ] Page locators are UPPERCASE method names?
- [ ] Step definitions extend/inject CommonStepDef?
- [ ] Page objects inject @Page annotations?
- [ ] Excel credentials have role/username/password columns?
- [ ] Feature files have tags for filtering?
- [ ] Tests use verify (not test) command?
- [ ] No shared mutable state in step definitions?