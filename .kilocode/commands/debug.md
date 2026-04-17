# Command: debug - Test Debugging

Debug failing Serenity BDD tests.

## Common Issues

1. **Test Not Found**
   - Use `mvn verify` - surefire is disabled
   - Check tag syntax: `-D"cucumber.filter.tags=@Tag"`

2. **Page Not Found**
   - Check `pages.<name>` in `serenity.conf`
   - Page name is case-sensitive (use lowercase)

3. **Element Not Found**
   - Check locators in page objects
   - Use UPPERCASE method names

4. **Parallel Test Issues**
   - 10-way parallelism can cause flakiness
   - Run with single thread: `-Dthreads=1`

## Debug Steps

```bash
# Run single tag
mvn clean verify -D"cucumber.filter.tags=@UISmoke"

# Run single scenario
mvn clean verify -D"cucumber.filter.tags=@Wikipedia"

# Run with visible browser
mvn clean verify -Dheadless.mode=false -D"cucumber.filter.tags=@Tag"

# Run single-threaded
mvn clean verify -Dthreads=1 -D"cucumber.filter.tags=@Tag"
```

## Logs and Reports

- **Serenity Report**: `target/site/serenity/index.html`
- **Test Output**: Check Maven output for step traces