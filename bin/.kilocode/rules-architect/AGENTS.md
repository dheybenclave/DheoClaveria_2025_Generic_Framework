# Project Architecture Rules (Non-Obvious Only)

- Execution architecture is Cucumber-on-JUnit-Platform via suite annotations in [CucumberTestSuite.java](../../src/test/java/CucumberTestSuite.java:8), not legacy JUnit runners.
- Build architecture intentionally routes test execution through failsafe lifecycle (`integration-test` + `verify`) in [pom.xml](../../pom.xml:153), with Serenity aggregation post-integration.
- Configuration architecture centralizes environment/page routing in [serenity.conf](../../src/test/resources/serenity.conf); step defs depend on `pages.*` keys at runtime.
- Cross-browser config is asymmetric: Chrome includes remote-grid toggle and richer switches in [serenity.conf](../../src/test/resources/serenity.conf:23), while other browsers are minimally remote-ready.
- Data-driven auth architecture is file-backed Excel lookup via [ExcelReader.getUsernameAndPasswordByRole()](../../src/test/java/utils/ExcelReader.java:173), coupled to expected column names (`role`, `username`, `password`).

