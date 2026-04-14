# DheoClaveria Generic Framework 2025

A Serenity BDD test automation framework with Cucumber, supporting local and remote WebDriver execution.

**🚀 [Quick Start Guide](QUICK_START.md)** | **📖 [GitHub Actions CI/CD](.github/workflows/maven.yml)**

## Requirements

- **Java 21 (LTS)** - Required for compilation and execution
- **Maven 3.8+**
- **Chrome/Firefox/Edge** - For local test execution

## Quick Start

### Local Execution

```bash
# Run tests with Maven
mvn clean verify -Denvironment=chrome -D"cucumber.filter.tags"="@UISmoke"
```

### GitHub Actions CI/CD

This project includes GitHub Actions workflow (`.github/workflows/maven.yml`) with:
- ✅ Local and RemoteWebDriver modes
- ✅ Cross-browser testing (Chrome, Edge, Firefox)
- ✅ Headless mode support
- ✅ Serenity BDD reporting
- ✅ JDK 21 auto-installation
- ✅ Tag-based test filtering via workflow_dispatch

## Project Structure

- `src/test/java/` - Test code (step definitions, page objects, utilities)
- `src/test/resources/features/` - Cucumber feature files
- `src/test/resources/serenity.conf` - Serenity configuration
- `.github/workflows/maven.yml` - GitHub Actions CI/CD workflow
- `pom.xml` - Maven dependencies and build configuration

## Configuration

### Browser Selection

Set via Maven property:
```bash
-Denvironment=chrome   # or edge, firefox
```

### Run Specific Tests

Using tags:
```bash
mvn clean verify -D"cucumber.filter.tags=@UISmoke"
```

### Serenity Reports

After test execution, view reports at:
```
target/site/serenity/index.html
```

## Documentation

- **[QUICK_START.md](QUICK_START.md)** - Getting started with the framework
- **[AGENTS.md](AGENTS.md)** - Developer guidelines and best practices
