# Project Coding Rules (Non-Obvious Only)

- Prefer Maven `verify` flows for runnable test changes because surefire is skipped in [pom.xml](../../pom.xml:130).
- Add new feature files only under [src/test/resources/features/](../../src/test/resources/features/) because [CucumberTestSuite.java](../../src/test/java/CucumberTestSuite.java:10) loads `/features` from classpath.
- Keep page-object locator methods uppercase-style (e.g., [DemoASPAwesomePage.SEARCH_PERSON_TXTBOX()](../../src/test/java/pages/DemoASPNETAwesome/DemoASPAwesomePage.java:23)); step defs are written around this style.
- For login/credential scenarios, preserve root-relative Excel path behavior in [LoginStepDef.GetCredentials()](../../src/test/java/stepdefinitions/ProDinnerASPNetAwesome/LoginStepDef.java:56).
- Avoid shared mutable scenario state in step defs because fixed 10-way parallel Cucumber execution is enabled in [junit-platform.properties](../../src/test/resources/junit-platform.properties:1).

