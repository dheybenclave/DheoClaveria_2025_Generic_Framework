# Azure Pipeline Setup Guide

## Overview

This project includes a complete Azure Pipelines configuration (`azure-pipelines.yml`) that supports:
- JDK 21 installation and Maven builds
- Cross-browser testing (Chrome, Edge, Firefox)
- Headless and headed modes
- Local WebDriver and RemoteWebDriver support
- Serenity BDD reporting with pipeline integration
- Chrome process cleanup and unique user-data-dir per run

## Quick Start

### 1. Create Pipeline in Azure DevOps

1. Navigate to **Pipelines** > **New Pipeline**
2. Select your repository source
3. Choose **Existing Azure Pipelines YAML file**
4. Select path: `/azure-pipelines.yml`
5. Click **Save** or **Run**

### 2. Configure Pipeline Parameters

When running the pipeline, you can customize these parameters:

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `tag` | string | `@UISmoke` | Cucumber tag filter (e.g., `@Regression`, `@Smoke`) |
| `browser` | choice | `chrome` | Browser to use: `chrome`, `edge`, or `firefox` |
| `headless` | choice | `false` | Run browser in headless mode: `true` or `false` |
| `useRemoteDriver` | choice | `false` | Use RemoteWebDriver instead of local driver |
| `remoteDriverUrl` | string | `http://localhost:4444/wd/hub` | Selenium Grid or RemoteWebDriver URL |

### 3. Optional: Configure Variables

Set these in **Pipelines** > **Edit** > **Variables**:

- `DOOR_ACCESS` (optional, secret): Passed as `-Daccess=$(DOOR_ACCESS)` to tests

## RemoteWebDriver Configuration

### When to Use RemoteWebDriver

Use RemoteWebDriver (`useRemoteDriver: true`) when:
- Running tests against a Selenium Grid
- Using containerized browsers (Docker Selenium)
- Connecting to BrowserStack, Sauce Labs, or similar services
- Running distributed tests across multiple machines

### How It Works

1. **Serenity Configuration**: The `serenity.conf` file includes conditional RemoteWebDriver support:
   ```hocon
   environments {
     chrome {
       webdriver {
         driver = chrome
         remote.url = ${?webdriver.remote.url}  # Optional, activated when set
         ...
       }
     }
   }
   ```

2. **Pipeline Parameter**: Set `useRemoteDriver: true` when running the pipeline

3. **Maven Property**: The pipeline passes `-Dwebdriver.remote.url="<url>"` to Maven

4. **Serenity Behavior**:
   - If `webdriver.remote.url` is set → Uses RemoteWebDriver
   - If not set → Uses local WebDriver with autodownload

### Example: Using Selenium Grid

#### Option A: Docker Selenium (Standalone)

```bash
# Start Selenium Grid with Chrome
docker run -d -p 4444:4444 --shm-size="2g" selenium/standalone-chrome:latest
```

Then run pipeline with:
- `useRemoteDriver: true`
- `remoteDriverUrl: http://localhost:4444/wd/hub`

#### Option B: Docker Selenium (Hub + Nodes)

```bash
# Start hub
docker run -d -p 4442-4444:4442-4444 --name selenium-hub selenium/hub:latest

# Start Chrome node
docker run -d --link selenium-hub:hub selenium/node-chrome:latest

# Start Firefox node
docker run -d --link selenium-hub:hub selenium/node-firefox:latest
```

Then run pipeline with:
- `useRemoteDriver: true`
- `remoteDriverUrl: http://selenium-hub:4444/wd/hub`

#### Option C: Cloud Services (BrowserStack, Sauce Labs)

For BrowserStack:
```yaml
# In pipeline variables, set:
BROWSERSTACK_USERNAME: your-username
BROWSERSTACK_ACCESS_KEY: your-access-key
```

Then run pipeline with:
- `useRemoteDriver: true`
- `remoteDriverUrl: https://$(BROWSERSTACK_USERNAME):$(BROWSERSTACK_ACCESS_KEY)@hub-cloud.browserstack.com/wd/hub`

### Local Testing with RemoteWebDriver

To test RemoteWebDriver locally before pipeline deployment:

```bash
# 1. Start Selenium Grid locally
docker run -d -p 4444:4444 --shm-size="2g" selenium/standalone-chrome:latest

# 2. Run tests with RemoteWebDriver
mvn clean verify \
  -Denvironment=chrome \
  -Dwebdriver.remote.url="http://localhost:4444/wd/hub" \
  -D"cucumber.filter.tags"="@UISmoke"
```

## Pipeline Execution Flow

1. **Cache Maven dependencies** (speeds up subsequent runs)
2. **Install JDK 21** (Linux agents only)
3. **Prepare WebDriver binaries** (cleanup .exe files on Linux)
4. **Pre-clean Chrome processes** (kills leftover processes)
5. **Run Maven tests** with configured parameters
6. **Publish test results** (JUnit XML)
7. **Publish Serenity report** (HTML artifact)
8. **Attach report to summary** (inline viewing)

## Advanced Configuration

### Running on Windows Agents

Change the pool to use Windows:

```yaml
pool:
  vmImage: windows-latest
```

The pipeline includes conditional steps for both Linux and Windows.

### Parallel Browser Testing

To run tests across multiple browsers simultaneously, use a matrix strategy:

```yaml
strategy:
  matrix:
    Chrome:
      browser: chrome
    Firefox:
      browser: firefox
    Edge:
      browser: edge
```

### Scheduled Runs

Add a schedule trigger for nightly regression:

```yaml
schedules:
  - cron: "0 2 * * *"  # 2 AM daily
    displayName: Nightly Regression
    branches:
      include: [ main ]
    always: true
```

### Pull Request Validation

Enable PR triggers:

```yaml
pr:
  branches:
    include:
      - main
      - develop
```

## Troubleshooting

### Issue: Chrome crashes in pipeline

**Solution**: Ensure headless mode is enabled for CI:
- Set `headless: true` parameter
- Or add `--headless=new` to Chrome args in `serenity.conf`

### Issue: RemoteWebDriver connection refused

**Checklist**:
1. Verify Selenium Grid is running: `curl http://localhost:4444/status`
2. Check network connectivity from agent to Grid
3. Ensure correct URL format: `http://host:port/wd/hub`
4. Verify browser capabilities match Grid configuration

### Issue: User data dir lock errors

**Solution**: Already handled by pipeline:
- Pre-clean step kills Chrome processes
- Unique user-data-dir per run: `chrome-profile-$(Agent.Id)-$(Build.BuildId)`

### Issue: Tests pass locally but fail in pipeline

**Common causes**:
1. Timing issues → Increase implicit waits in `serenity.conf`
2. Screen resolution → Add `--window-size=1920,1080` to Chrome args
3. Missing dependencies → Check JDK version and Maven dependencies

## Best Practices

1. **Use headless mode in CI**: Set `headless: true` for faster, more stable runs
2. **Tag your tests**: Use Cucumber tags (`@Smoke`, `@Regression`) for selective execution
3. **Monitor Grid capacity**: Ensure Selenium Grid has enough nodes for parallel execution
4. **Secure credentials**: Store sensitive data (API keys, passwords) in Azure Pipeline Variables as secrets
5. **Review reports**: Always check Serenity reports in pipeline artifacts for detailed test results

## Support

For issues or questions:
1. Check Serenity BDD documentation: https://serenity-bdd.github.io/
2. Review pipeline logs in Azure DevOps
3. Validate local execution before pipeline deployment
