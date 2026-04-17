# After File Edit Hook

Reminds quick checks after file edits in the project.

## What It Does

After editing feature files, step definitions, or page objects:

1. **Verify Compilation**
   - Run: `mvn compile` to check for errors
   - Verify imports are correct

2. **Quick Test**
   - Run: `mvn verify -DskipTests=true` to check test discovery
   - Verify step definitions are recognized

## Usage

```bash
# Auto-triggered after file edits
# Or manually:
mvn compile
```

## Common Checks

- Feature file syntax: `mvn verify -DskipTests=true`
- Page object compilation: `mvn compile`
- Step definitions: Check for missing steps in output