# DheoClaveria Generic Framework 2025

A Serenity BDD test automation framework with Cucumber, supporting local and remote WebDriver execution.

**🚀 [Quick Start Guide](QUICK_START.md)** | **🌐 [Selenium Grid Complete Guide](SELENIUM_GRID_COMPLETE.md)** | **📖 [Grid Setup Details](SELENIUM_GRID_SETUP.md)** | **☁️ [Azure Pipeline Setup](AZURE_PIPELINE_SETUP.md)**

## Requirements

- **Java 21 (LTS)** - Required for compilation and execution
- **Maven 3.8+** or **Gradle 8.14+**
- **Chrome/Firefox/Edge** - For local test execution

## Quick Start

### Local Execution

```bash
# Run tests with Maven
mvn clean verify -Denvironment=chrome -D"cucumber.filter.tags"="@UISmoke"

# Run tests with Gradle
./gradlew clean test
```

### Azure Pipeline Execution

This project includes full Azure Pipelines support with:
- ✅ Local and RemoteWebDriver modes
- ✅ Cross-browser testing (Chrome, Edge, Firefox)
- ✅ Headless mode support
- ✅ Serenity BDD reporting
- ✅ JDK 21 auto-installation

**📖 See [AZURE_PIPELINE_SETUP.md](AZURE_PIPELINE_SETUP.md) for complete setup and deployment instructions.**

## Project Structure

- `src/test/java/` - Test code (step definitions, page objects, utilities)
- `src/test/resources/features/` - Cucumber feature files
- `src/test/resources/serenity.conf` - Serenity configuration
- `azure-pipelines.yml` - Azure DevOps pipeline configuration
- `pom.xml` - Maven dependencies and build configuration

## Configuration

### Browser Selection

Set via Maven property:
```bash
-Denvironment=chrome   # or edge, firefox
```

### Headless Mode

```bash
-Dheadless.mode=true
```

### RemoteWebDriver (Selenium Grid)

```bash
-Dwebdriver.remote.url="http://localhost:4444/wd/hub"
```

**📖 See [SELENIUM_GRID_SETUP.md](SELENIUM_GRID_SETUP.md) for complete Selenium Grid setup with Docker.**

**Quick Start:**
```bash
# Windows
start-selenium-grid.bat

# Linux/Mac
./start-selenium-grid.sh

# Run tests against Grid
mvn clean verify -Dwebdriver.remote.url="http://localhost:4444/wd/hub"
```

## Technology Stack

- **Serenity BDD 4.2.12** - Test reporting and WebDriver management
- **Cucumber 7.16.1** - BDD test scenarios
- **JUnit 5.10.2** - Test execution platform
- **Selenium 4.21.0** - Browser automation
- **Java 21** - Programming language

## Notes

- Maven builds use the maven-compiler-plugin with `<release>21</release>`
- Gradle builds use a Java toolchain configured to languageVersion 21
- Gradle will auto-download a suitable JDK if `org.gradle.java.installations.auto-download=true` is set in `gradle.properties`
- WebDriver binaries are auto-downloaded by Serenity (no manual driver management needed)

## Reports

After test execution, view the Serenity report:
```
target/site/serenity/index.html
```


