# Quick Start Guide

> **⚠️ Critical**: Use `mvn clean verify` (NOT `mvn test`) - surefire is disabled in pom.xml.

## 🚀 Run Tests Locally

```bash
# Chrome (default)
mvn clean verify -D"cucumber.filter.tags"="@UISmoke"

# Firefox
mvn clean verify -Denvironment=firefox -D"cucumber.filter.tags"="@UISmoke"

# Headless mode
mvn clean verify -Dheadless.mode=true -D"cucumber.filter.tags"="@UISmoke"
```

## ☁️ Run in GitHub Actions

The workflow runs automatically on push/PR to `main`/`master`, or manually via workflow_dispatch:

- **Tag**: `@UISmoke` (or your custom tag)
- **Browser**: `chrome` (default), `edge`, or `firefox`

Reports are uploaded as artifacts:
- Serenity Report: `target/site/serenity/`
- Timeline Report: `build/test-results/timeline/`

## 📊 View Reports

```bash
# Open Serenity report
start target/site/serenity/index.html        # Windows
open target/site/serenity/index.html          # Mac
xdg-open target/site/serenity/index.html     # Linux
```

## 🔧 Common Commands

```bash
# Run specific feature by tag
mvn clean verify -D"cucumber.filter.tags"="@CreateMeal"

# Run all tests
mvn clean verify

# Run with specific environment
mvn clean verify -Denvironment=chrome -Dheadless.mode=true

# Skip tests (compile only)
mvn clean compile -DskipTests

# Update dependencies
mvn versions:display-dependency-updates

# Clean everything
mvn clean
```

## 🐛 Troubleshooting

**Chrome crashes?**
- Use headless mode: `-Dheadless.mode=true`

**Tests fail in CI but pass locally?**
- Check browser versions match (CI uses Chrome stable)
- Increase timeouts in `serenity.conf`

**Feature files not found?**
- Ensure they are under `src/test/resources/features/` (classpath-rooted)

## 🎯 Test Tags

Use Cucumber tags to run specific test suites:

- `@UISmoke` - Quick smoke tests
- `@Regression` - Full regression suite
- `@CreateMeal` - Meal creation tests
- `@Login` - Authentication tests

Example:
```bash
mvn clean verify -D"cucumber.filter.tags"="@Regression and not @Slow"
```

## 📚 Documentation

- **[AGENTS.md](AGENTS.md)** - Agent guidance for AI assistants
- **[README.md](README.md)** - Project overview

## 💡 Pro Tips

1. **Tag your tests** - Run only what you need
2. **Use headless in CI** - More stable and faster
3. **Check reports** - Review Serenity HTML reports for debugging
4. **Run from project root** - Excel paths are root-relative
