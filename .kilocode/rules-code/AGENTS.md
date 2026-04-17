# Project Coding Rules (Non-Obvious Only)

## Commands

- **Run tests**: `mvn clean verify` (must use verify, not test)
- **Feature files**: Must be under `src/test/resources/features/` (classpath-rooted)

## Page Object Pattern

- **Locator methods**: UPPERCASE (e.g., `SEARCH_PERSON_TXTBOX()`)
- See [DemoASPAwesomePage.java](src/test/java/pages/DemoASPNETAwesome/DemoASPAwesomePage.java:23)
- Step definitions are written around this uppercase style

## Step Definition Hooks

- **@Steps**: Inject CommonStepDef for shared methods
- **@Page**: Inject page objects
- **@Before/@After**: Cucumber hooks
- **@WhenPageOpens**: Page load hooks

## Credential Hooks

- Excel path: `System.getProperty("user.dir") + "/src/test/resources/testData/" + file + ".xlsx"`
- Required columns: `role`, `username`, `password`
- Method: [ExcelReader.getUsernameAndPasswordByRole()](src/test/java/utils/ExcelReader.java:173)

## Parallelism

- 10-way parallel execution enabled in [junit-platform.properties:3](src/test/resources/junit-platform.properties:3)
- Avoid shared mutable state in step definitions

## Common Methods (via CommonStepDef)

- `navigatePage(page)` - Navigate via pages.* config
- `clickElement(element)` - Click with JS fallback
- `enterText(element, value, waitMs)` - Type text
- `verifyVisibilityofElement(element)` - Assert visible
- `testStep(message)` - Log test steps
