# AGENTS.md

This file provides guidance to agents working with code in this repository.

## Project Overview

- **Stack**: Serenity BDD 4.1.12, Cucumber 7.16.1, Java 21 (LTS), Selenium WebDriver, Maven 3.8+
- **Domain**: UI automation for configurable web applications
- **Framework**: Cucumber-on-JUnit-Platform (JUnit 5.10.2) with rich page objects
- **Parallelism**: 10-way concurrent execution with thread-safe test isolation

## Architecture

| Path | Description |
|------|-------------|
| `src/test/resources/features/{Module}/` | Gherkin scenarios with `@Tag` markers (organized by module) |
| `src/test/java/stepdefinitions/{Module}/` | Step definitions (thin glue, module-specific) |
| `src/test/java/pages/{Module}/` | Page objects with locators (rich, module-specific) |
| `src/test/java/pages/CommonPage.java` | Shared locators across all modules |
| `src/test/java/pages/{Module}/Common{Module}Page.java` | Module-wide shared locators |
| `src/test/java/utils/` | Utilities (ExcelReader, JsonReader, BaseClass, Utilities) |
| `src/test/resources/serenity.conf` | Environment configurations |
| `src/test/resources/testData/` | Test credentials (Excel files) and JSON test data |
| `target/site/serenity/` | HTML reports |

**Module-Based Organization:**
- `DemoASPNETAwesome` - Grid filtering and CRUD operations
- `ProDinnerPage` - Meal planning and authentication
- `Wikipedia` - Search functionality
- `AutomationExercise` - E-commerce workflows

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
| `headless.mode` | Browser mode (true=headless, false=headed) | true |
| `webdriver.remote.url` | Remote Grid URL for RemoteWebDriver | (empty) |
| `cucumber.filter.tags` | Cucumber tag filter (e.g., `@UISmoke`, `@Regression and not @Slow`) | (all) |

**Parallel Execution Configuration:**
- Controlled by `src/test/resources/junit-platform.properties`
- Default: 10 parallel threads (`cucumber.execution.parallel.config.fixed.parallelism=10`)
- Ensure step definitions use no shared mutable state

## Code Standards

### Page Objects

- **Locator methods**: UPPERCASE (e.g., `SEARCH_PERSON_TXTBOX()`)
- **Return type**: `WebElementFacade` (single) or `ListOfWebElementFacades` (multiple)
- **Inheritance hierarchy**: `UIInteractions` ← `CommonPage` ← `Common{Module}Page` ← `{Module}Page`

```java
public class LoginPage extends CommonProDinnerPage {
    @FindBy(id = "username")
    private WebElementFacade txtUsername;

    public WebElementFacade SEARCH_PERSON_TXTBOX() {
        return txtUsername;
    }
}
```

**Common patterns from BaseClass:**
- Call `injectAdBlocker()` after navigation to block ads (inherited via CommonPage)
- Call `dismissCookieBanner()` in CommonStepDef.navigatePage() to handle consent overlays

### Step Definitions

- **@Steps**: Inject `CommonStepDef` for shared methods
- **@Page**: Inject page objects (use plural field names: `demoPage`, `loginPage`)
- **Parallelism**: 10-way - avoid shared mutable state at class level
- **Step annotation pattern**: Use English phrase matching Cucumber expressions

```java
public class LoginStepDef {
    @Steps
    CommonStepDef commonStepDef;

    @Page
    LoginPage loginPage;
    
    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        commonStepDef.navigatePage("login");  // Uses pages.login from serenity.conf
        commonStepDef.dismissCookieBanner();
    }
    
    @When("I enter credentials for {string}")
    public void iEnterCredentialsFor(String role) {
        Map<String, String> creds = ExcelReader.getUsernameAndPasswordByRole(..., role);
        commonStepDef.enterText(loginPage.USERNAME_FIELD(), creds.get("username"), 1000);
    }
}
```

**Key CommonStepDef methods available:**
- `navigatePage(page)` - Navigate to page by name (lowercase)
- `clickElement(element)` - Click with JS fallback for intercepted clicks
- `enterText(element, value, waitMs)` - Type text with visibility check
- `verifyVisibilityofElement(element)` - Assert element is visible
- `waitForPageInSecond(ms)` - Explicit wait
- `testStep(message)` - Log test step to Serenity report
- `dismissCookieBanner()` - Handle common consent overlays

### Credentials (Excel)

- Excel path: `src/test/resources/testData/{filename}.xlsx`
- Required columns: `role`, `username`, `password`
- Method: `ExcelReader.getUsernameAndPasswordByRole(filePath, sheetName, role)`
- Example files: `userCredentials.xlsx`, `invalidUserCredentials.xlsx`

```java
String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/userCredentials.xlsx";
Map<String, String> creds = ExcelReader.getUsernameAndPasswordByRole(filePath, "UserList", "Admin");
```

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
- Timeline: `build/test-results/timeline/`

## Utilities & Helpers

| Utility | Location | Key Methods |
|---------|----------|------------|
| **ExcelReader** | `utils/ExcelReader.java` | `getUsernameAndPasswordByRole(filePath, sheet, role)`, `getData(filePath, sheet)` |
| **JsonReader** | `utils/JsonReader.java` | `readJsonFile(filePath)`, `getGridExpectedResults(filePath, filters)` |
| **Utilities** | `utils/Utilities.java` | `generateRandomString()`, `generateRandomInteger(min, max)` |
| **BaseClass** | `utils/BaseClass.java` | `injectAdBlocker()` (called by CommonStepDef.navigatePage) |

All step definitions inherit methods from `CommonStepDef`, which extends `BaseClass`.

## Mode-Specific Rules

See `.kilocode/rules-*/AGENTS.md` for:
- **code** - Coding rules & patterns
- **debug** - Debugging commands & hooks
- **ask** - Documentation context
- **architect** - Architecture constraints

## Kilo Commands & Hooks

See `.kilocode/commands/` and `.kilocode/hooks/` for Kilo-specific automation.

### Migrated Commands

| Command | Description |
|---------|-------------|
| `feature-development` | Workflow scaffold for Serenity BDD feature development |
| `test-debugging` | Triage flow for failing Serenity BDD tests |
| `self-heal-tests` | Stabilize flaky tests with locator fallback |
| `generate-test-cases` | Generate test cases from requirements |
| `plan-regression-suite` | Plan marker-based regression suites |
| `agentic-ci-cd` | Configure agentic QA in GitHub Actions |

### Migrated Agents

| Agent | Description |
|-------|-------------|
| `qa-test-automation-engineer` | QA automation specialist |
| `test-architect` | Test architecture design |
| `product-owner-business-analyst` | Requirements analysis |
| `scrum-team-leader` | Project coordination |

### MCP Servers

| Server | Description |
|--------|-------------|
| `playwright-local` | Playwright MCP for browser automation |
| `playwright` | Playwright MCP (specific version) |
| `n8n-mcp` | n8n workflow automation |
| `filesystem-project` | Filesystem access for project |

## Testing Requirements

When implementing or fixing behavior:
1. Run targeted scenario/tag first (fast feedback): `mvn clean verify -D"cucumber.filter.tags=@YourTag"`
2. Run module-specific tests: `mvn clean verify -D"cucumber.filter.tags=@DemoASPAwesome"`
3. Run discovery check: `mvn verify -DskipTests` to catch compilation/classpath issues
4. Run broader regression selection before finalizing: `mvn verify -D"cucumber.filter.tags=@Regression"`

**Parallelism Implications:**
- 10 threads execute scenarios concurrently (see `junit-platform.properties`)
- Each thread gets independent WebDriver instance (via Serenity thread-local storage)
- Avoid instance fields in step definitions that accumulate state across threads
- Use local variables in step methods to isolate data

### Stability Rules

- Keep step definitions thin and delegate UI actions/assertions to page objects
- Avoid brittle selectors in steps; keep selectors inside page objects (use UPPERCASE methods)
- Prefer deterministic waits: `element.waitUntilVisible().withTimeoutOf(15, TimeUnit.SECONDS)` over arbitrary sleeps
- Preserve tag-driven execution (`@UISmoke`, `@Regression`, module tags like `@DemoASPAwesome`)
- Use `commonStepDef.testStep(message)` to log steps clearly (appears in Serenity report)

### Definition of Done (Testing)

- [ ] Scenario(s) for the change pass
- [ ] `mvn verify` passes
- [ ] No new flaky waits or timing hacks
- [ ] Report artifacts remain generated
