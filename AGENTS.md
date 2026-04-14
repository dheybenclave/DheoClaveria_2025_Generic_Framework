# AGENTS.md - Agent Guidance for This Repository

This file provides guidance to agents working with code in this repository.

## Build, Test & Execution Commands

### Running Tests

```bash
# Run all tests (uses failsafe, not surefire)
mvn clean verify

# Run single scenario by tag (primary method)
mvn clean verify -D"cucumber.filter.tags=@your_tag"

# Run with specific browser environment (chrome|edge|firefox)
mvn clean verify -Denvironment=chrome -D"cucumber.filter.tags=@your_tag"

# Run with remote grid URL
mvn clean verify -Dwebdriver.remote.url=http://localhost:4444/wd/hub -Denvironment=chrome
```

### Key Notes

- **Use `verify`, NOT `test`** - surefire is disabled in [pom.xml](pom.xml:136)
- Tests run via Maven failsafe lifecycle (`integration-test` + `verify`)
- Parallelism is fixed at 10 - do not assume scenario execution order
- Serenity reports generated at `build/test-results/timeline`

---

## Code Style Guidelines

### Naming Conventions

- **Page Object Locators**: UPPERCASE method names (e.g., `SEARCH_PERSON_TXTBOX()`)
  - See [CommonPage.java](src/test/java/pages/CommonPage.java:8) for pattern
- **Step Definitions**: PascalCase with descriptive names (e.g., `LoginStepDef.java`)
- **Feature Files**: Lowercase with underscores (e.g., `login.feature`)

### Project Structure

```
src/test/java/
├── stepdefinitions/     # Cucumber step definitions (extend PageComponent)
├── pages/              # Page object models (extend UIInteractions)
└── utils/              # Utility classes (ExcelReader, etc.)

src/test/resources/
├── features/           # Gherkin .feature files (classpath-rooted)
├── serenity.conf       # Environment & page URL configuration
└── testData/           # Test data (Excel files for credentials)
```

### Imports

- Serenity: `net.serenitybdd.annotations.*`, `net.serenitybdd.screenplay.*`
- Cucumber: `io.cucumber.java.en.*`, `io.cucumber.datatable.*`
- Selenium: `org.openqa.selenium.*`
- Use SLF4J for logging: `org.slf4j.*`

### Coding Patterns

1. **Page Navigation**: Use `CommonStepDef.thePage()` which reads from `pages.*` keys in serenity.conf
2. **Credentials**: Use `ExcelReader.getUsernameAndPasswordByRole()` - requires Excel file with columns: `role`, `username`, `password`
3. **Parallel Safety**: Avoid shared mutable state in step definitions
4. **Element Locators**: Return `WebElementFacade` from page methods

### Error Handling

- Unknown page keys throw `UnknownPageException`
- Use Serenity's `shouldBeVisible()`, `isPresent()`, `isVisible()` for assertions
- Prefer `waitABit()` for simple waits (not for complex synchronization)

---

## Architecture

### Test Execution Flow

1. [CucumberTestSuite.java](src/test/java/CucumberTestSuite.java:8) - Suite annotations (NOT legacy JUnit runners)
2. Failsafe plugin runs integration-test phase
3. Serenity plugin aggregates reports in post-integration-test

### Configuration

- **serenity.conf**: Central environment/browser/page routing
- `-Denvironment=chrome|edge|firefox` maps to environment blocks
- Only Chrome has `remote.url` wired for grid execution
- Chrome uses per-run profile isolation: `--user-data-dir=target/chrome-profile-${random.int}`

### Feature File Discovery

- Classpath-rooted to `/features` (NOT file-system rooted)
- Must be under `src/test/resources/features/`
- Tag-driven scenario selection only

---

## Debugging

- Debug with `mvn verify` (not `mvn test`)
- Scenario debugging via Cucumber tags: `-D"cucumber.filter.tags=@tag"`
- URL navigation failures: check `pages.<name>` entries in serenity.conf
- Intermittent timing issues: likely amplified by parallelism=10
- Chrome profile contamination: check serenity.conf overrides

---

## Important File Locations

| File | Purpose |
|------|---------|
| `pom.xml` | Build config, surefire skipped |
| `serenity.conf` | Environment & page configs |
| `junit-platform.properties` | Parallelism settings |
| `CucumberTestSuite.java` | Test suite loader |
| `ExcelReader.java` | Credential lookup utility |

---

## Non-Obvious Rules

1. Run commands from repo root (Excel paths are root-relative)
2. Feature files MUST stay under `src/test/resources/features/`
3. Keep page locator methods uppercase for step def consistency
4. Chrome remote-grid toggle available; Edge/Firefox are local-centric
5. Timeline reports go to `build/test-results/timeline` even for Maven runs
