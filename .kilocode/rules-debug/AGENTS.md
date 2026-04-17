# Project Debug Rules (Non-Obvious Only)

## Commands

- **Run tests**: `mvn clean verify` (NOT `mvn test` - surefire disabled)
- **Single scenario**: `mvn clean verify -D"cucumber.filter.tags=@tag"`
- **Debug tag**: `@UISmoke` for quick smoke tests

## Debug Hooks & Patterns

- **Test appears not to run**: Use `mvn verify` - surefire is disabled in [pom.xml:136](pom.xml:136)
- **Scenario debugging**: Cucumber tags (`-D"cucumber.filter.tags=@tag"`)
- **URL navigation failures**: Check `pages.<name>` in [serenity.conf](src/test/resources/serenity.conf:105), surfaced by [CommonStepDef.thePage()](src/test/java/stepdefinitions/CommonStepDef.java:81)
- **Intermittent UI timing**: parallelism=10 in [junit-platform.properties:3](src/test/resources/junit-platform.properties:3); isolate with tag runs
- **Chrome profile issues**: Dynamic profile dirs in [serenity.conf](src/test/resources/serenity.conf:26) should prevent; check overrides

## Logging

- Use `commonStepDef.testStep(message)` for test step logging
- Use `logger.info()` for debug output
- Serenity reports at `target/site/serenity/`
- Timeline reports at `build/test-results/timeline/`

## CI Debug

- GitHub Actions workflow at [.github/workflows/maven.yml](.github/workflows/maven.yml)
- Uploaded artifacts: serenity-report, timeline-report
