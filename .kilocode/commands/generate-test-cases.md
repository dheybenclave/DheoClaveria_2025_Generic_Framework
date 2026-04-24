# Command: generate-test-cases

Generate Serenity BDD test cases from requirements using existing framework patterns.

## Inputs

- Requirement statement or user story
- Target marker/tag (example: `@TC12`)
- Expected positive and negative behaviors

## Output Targets

- Feature file: `src/test/resources/features/**`
- Step glue: `src/test/java/stepdefinitions/**`
- Page updates (if needed): `src/test/java/pages/**`

## Rules

- Keep steps thin; put locator/action/assertion logic in page objects.
- Reuse existing step patterns before adding new step definitions.
- Include scenario outline examples for data-driven cases.

## Example Prompt

"Generate login and lockout test cases for invalid credentials and add feature + steps using existing page object methods."