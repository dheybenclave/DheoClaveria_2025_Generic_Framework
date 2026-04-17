# Command: collect - Test Collection

Collect and list all test cases without running them.

## What It Does

1. **List Feature Files**
   - Show all `.feature` files in `src/test/resources/features/`
   - Count scenarios per feature

2. **Show Test Tags**
   - List all Cucumber tags (`@UISmoke`, `@Regression`, etc.)
   - Show scenarios associated with each tag

3. **Display Test Coverage**
   - Show step definitions available
   - Show page objects available

## Usage

```bash
# Collect without running
mvn clean verify -DskipTests=true

# List feature files
dir src\test\resources\features\ /s /b

# Or ask Kilo
kilo "Collect all test cases"
```

## Expected Output

- Feature file listing
- Tag summary
- Step definition count