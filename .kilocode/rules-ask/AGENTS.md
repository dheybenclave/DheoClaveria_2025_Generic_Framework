# Project Documentation Rules (Non-Obvious Only)

- Document Maven execution using `verify`, not `test`, due to explicit surefire skip in [pom.xml](../../pom.xml:130).
- Clarify that only Chrome environment currently wires `webdriver.remote.url` in [serenity.conf](../../src/test/resources/serenity.conf:29); Edge/Firefox blocks are local-centric.
- Mention that feature discovery is classpath `/features` from [CucumberTestSuite.java](../../src/test/java/CucumberTestSuite.java:10), so relocating features breaks suite pickup.
- Note that timeline output path is configured in [CucumberTestSuite.java](../../src/test/java/CucumberTestSuite.java:11) under `build/test-results/timeline`.
- Mention root-directory execution requirement for Excel-backed credentials in [LoginStepDef.java](../../src/test/java/stepdefinitions/ProDinnerASPNetAwesome/LoginStepDef.java:63).

