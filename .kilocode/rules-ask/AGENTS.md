# Project Documentation Rules (Non-Obvious Only)

## Key Documentation

- **Build**: Use `verify`, not `test` - surefire disabled in [pom.xml:136](pom.xml:136)
- **Browser**: Only Chrome has webdriver.remote.url in [serenity.conf:29](src/test/resources/serenity.conf:29); Edge/Firefox local-only
- **Features**: Classpath `/features` from [CucumberTestSuite.java:10](src/test/java/CucumberTestSuite.java:10)
- **Reports**: Timeline at `build/test-results/timeline/`
- **Excel paths**: Root-relative (run from project root)

## Project Structure

```
src/test/java/
├── stepdefinitions/     # Cucumber steps
├── pages/           # Page objects
└── utils/           # Utilities

src/test/resources/
├── features/        # Gherkin .feature files
├── testData/        # Excel + JSON data
└── serenity.conf   # Environment configs
```

## Utilities

- [ExcelReader.java](src/test/java/utils/ExcelReader.java) - Excel credential lookup
- [JsonReader.java](src/test/java/utils/JsonReader.java) - JSON data reading
- [Utilities.java](src/test/java/utils/Utilities.java) - Random string generation

## References

- [AGENTS.md](AGENTS.md) - Agent guidance (concise)
- [CODE_STANDARDS.md](CODE_STANDARDS.md) - Full coding standards
- [QUICK_START.md](QUICK_START.md) - Quick start guide


## Code Standards & Best Practices Guide
- See `CODE_STANDARDS.md`