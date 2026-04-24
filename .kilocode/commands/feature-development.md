# Command: feature-development

Workflow scaffold for Serenity BDD feature development.

## Goal

Add or update behavior with aligned feature, step, and page object layers.

## Common Files

- `src/test/resources/features/**/*.feature`
- `src/test/java/stepdefinitions/**/*.java`
- `src/test/java/pages/**/*.java`
- `src/test/java/utils/**/*.java`

## Suggested Sequence

1. Read current scenario behavior and expected outcomes.
2. Update feature text/tables if business behavior changed.
3. Update step definitions with minimal glue logic.
4. Implement locator/action/assertion changes in page objects.
5. Run targeted tag/scenario.
6. Run `mvn verify` before finalizing.

## Typical Commit Signals

- Add feature scenario coverage
- Update step glue and page behavior
- Improve framework stability or selector reliability