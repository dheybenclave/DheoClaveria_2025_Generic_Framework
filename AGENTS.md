# AGENTS.md

This file provides guidance to agents working with code in this repository.

## Project Overview

- **Stack**: Serenity BDD, Cucumber, Java, Selenium WebDriver, Maven
- **Domain**: UI automation for configurable web applications
- **Framework**: Cucumber-on-JUnit-Platform with rich page objects

## Architecture

| Path | Description |
|------|-------------|
| `src/test/resources/features/*.feature` | Gherkin scenarios with `@Tag` markers |
| `src/test/java/stepdefinitions/` | Step definitions (thin glue) |
| `src/test/java/pages/` | Page objects with locators (rich) |
| `src/test/java/utils/` | Utilities (ExcelReader, JsonReader) |
| `src/test/resources/serenity.conf` | Environment configurations |
| `target/site/serenity/` | HTML reports |

## Quick Start Commands

```bash
# CRITICAL: Use verify, NOT test - surefire is disabled
mvn clean verify

# Run specific tag
mvn clean verify -D"cucumber.filter.tags=@UISmoke"

# Run with browser
mvn clean verify -Denvironment=chrome -D"cucumber.filter.tags=@UISmoke"

# Headless mode
mvn clean verify -Dheadless.mode=true -D"cucumber.filter.tags=@UISmoke"

# Single test by tag
mvn clean verify -D"cucumber.filter.tags=@Wikipedia"
```

## Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `environment` | Target environment (chrome, edge, firefox) | chrome |
| `headless.mode` | Browser mode | true |
| `webdriver.remote.url` | Remote Grid URL | (empty) |

## Code Standards

### Page Objects

- **Locator methods**: UPPERCASE (e.g., `SEARCH_PERSON_TXTBOX()`)
- **Return type**: `WebElementFacade`
- **Inheritance**: Extend `UIInteractions` or common pages

```java
public class LoginPage extends CommonPage {
    @FindBy(id = "username")
    private WebElementFacade txtUsername;

    public WebElementFacade SEARCH_PERSON_TXTBOX() {
        return txtUsername;
    }
}
```

### Step Definitions

- **@Steps**: Inject CommonStepDef for shared methods
- **@Page**: Inject page objects
- **Parallelism**: 10-way - avoid shared mutable state

```java
public class LoginStepDef {
    @Steps
    CommonStepDef commonStepDef;

    @Page
    LoginPage loginPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        commonStepDef.navigatePage("login");
    }
}
```

### Credentials (Excel)

- Excel file must have columns: `role`, `username`, `password`
- Method: `ExcelReader.getUsernameAndPasswordByRole(filePath, sheetName, role)`

## Page Routing

- Uses `pages.*` keys in `serenity.conf`
- Page name is case-sensitive - use lowercase when navigating
- Add new pages to `serenity.conf`:

```properties
pages {
  wikipedia = "https://wikipedia.org"
}
```

## Reports

- Serenity: `target/site/serenity/index.html`
- Timeline: `target/test-results/timeline/`

## Mode-Specific Rules

See `.kilocode/rules-*/AGENTS.md` for:
- **code** - Coding rules & patterns
- **debug** - Debugging commands & hooks
- **ask** - Documentation context
- **architect** - Architecture constraints

## Kilo Commands & Hooks

See `.kilocode/commands/` and `.kilocode/hooks/` for Kilo-specific automation.
