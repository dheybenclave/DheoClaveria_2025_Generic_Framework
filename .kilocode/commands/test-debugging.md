# Command: test-debugging

Fast triage flow for failing Serenity BDD tests.

## Triage Flow

1. Reproduce with the smallest failing tag/scenario.
2. Confirm collection integrity with `mvn verify -DskipTests`.
3. Identify failure layer:
   - Feature expectation mismatch
   - Step glue mismatch
   - Page locator/action/assertion mismatch
4. Apply fix in the lowest valid layer (prefer page object first).
5. Re-run failing scenario, then related tags.

## Debug Commands

```bash
mvn verify -Denvironment=chrome -Dheadless.mode=false -Dcucumber.filter.tags=@Tag
mvn verify -DskipTests -Dcucumber.filter.tags=@Tag
mvn verify -Dcucumber.filter.tags="@Tag1 or @Tag2"
```

## Completion Criteria

- Failure reproduced and root cause documented
- Fix validated in targeted and related runs
- No collection regressions