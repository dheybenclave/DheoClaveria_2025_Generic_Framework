# Test Automation Guardrails

## Core Principles

1. **Test Isolation**: Each test must be independent and not depend on other tests
2. **Deterministic Execution**: Tests must produce consistent results
3. **Clear Assertions**: Use descriptive assertion messages
4. **Proper Cleanup**: Clean up test data and state after tests

## Security Requirements

- Never hardcode credentials - use environment variables or Excel lookups
- Never print secrets in logs
- Use role-based authentication
- Store credentials in `.env` files (git-ignored)

## Test Data Management

- Use Excel files for credential lookups: `src/test/resources/testData/`
- Required columns: `role`, `username`, `password`
- Method: `ExcelReader.getUsernameAndPasswordByRole()`

## Locator Best Practices

- Use semantic selectors over fragile CSS
- Prefer `data-qa` attributes when available
- Add fallback locators for resilience
- Keep locators in page objects, not step definitions

## Wait Strategies

- Use explicit waits: `waitUntilVisible()`, `waitForPageToLoad()`
- Avoid `time.sleep()` - use deterministic waits
- Set reasonable timeouts (15 seconds default)

## Reporting

- Serenity report: `target/site/serenity/index.html`
- Test artifacts: `target/site/serenity/`