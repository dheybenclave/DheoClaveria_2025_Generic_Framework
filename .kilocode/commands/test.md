# Command: test - Run Tests

Execute Serenity BDD tests.

## Test Execution

```bash
# Run all tests (CRITICAL: use verify, NOT test)
mvn clean verify

# Run specific tag
mvn clean verify -D"cucumber.filter.tags=@UISmoke"

# Run with browser
mvn clean verify -Denvironment=chrome -D"cucumber.filter.tags=@UISmoke"

# Headless mode (default)
mvn clean verify -Dheadless.mode=true -D"cucumber.filter.tags=@UISmoke"
```

## Tags

| Tag | Description |
|-----|-------------|
| `@UISmoke` | Quick smoke tests |
| `@Regression` | Full regression suite |
| `@Wikipedia` | Wikipedia test example |
| Any custom | Feature-specific tags |

## Reports

- **Serenity Report**: `target/site/serenity/index.html`
- **Timeline**: `target/test-results/timeline/`