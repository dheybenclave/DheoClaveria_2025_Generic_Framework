# Project Architecture Rules (Non-Obvious Only)

## Architecture

- **Test runner**: Cucumber-on-JUnit-Platform via [CucumberTestSuite.java](src/test/java/CucumberTestSuite.java:8)
- **Build**: Failsafe lifecycle (`integration-test` + `verify`) in [pom.xml](pom.xml:153)
- **Serenity aggregation**: Post-integration-test phase
- **Configuration**: [serenity.conf](src/test/resources/serenity.conf) with pages.* keys

## Cross-Browser

- **Chrome**: remote-grid toggle + richer switches enabled
- **Edge/Firefox**: Local-only, minimal config

## Data-Driven Auth

- **Excel lookup**: [ExcelReader.getUsernameAndPasswordByRole()](src/test/java/utils/ExcelReader.java:173)
- **Required columns**: `role`, `username`, `password`

## Parallelism

- **10-way execution**: Fixed in [junit-platform.properties:3](src/test/resources/junit-platform.properties:3)
- **Avoid**: Shared mutable state in step definitions

## Page Routing

- Uses `pages.*` keys in [serenity.conf](src/test/resources/serenity.conf:106)
- [CommonStepDef.thePage()](src/test/java/stepdefinitions/CommonStepDef.java:81) reads at runtime
