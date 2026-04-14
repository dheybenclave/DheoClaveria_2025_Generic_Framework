# Project Debug Rules (Non-Obvious Only)

- If tests appear not to run with `mvn test`, this is expected: surefire is disabled in [pom.xml](../../pom.xml:130); debug with `mvn verify`.
- Scenario-selection debugging is done with Cucumber tags (`-D"cucumber.filter.tags=@tag"`) as documented in [command_test_run.txt](../../src/main/java/starter/command_test_run.txt:1).
- URL navigation failures usually mean missing `pages.<name>` entries in [serenity.conf](../../src/test/resources/serenity.conf:105), surfaced by [CommonStepDef.thePage()](../../src/test/java/stepdefinitions/CommonStepDef.java:81).
- Intermittent UI timing issues are amplified by parallelism=10 in [junit-platform.properties](../../src/test/resources/junit-platform.properties:3); reproduce with isolated tag runs.
- Chrome profile contamination bugs should be rare because runs use dynamic profile dirs in [serenity.conf](../../src/test/resources/serenity.conf:26); check overrides if sessions leak.

