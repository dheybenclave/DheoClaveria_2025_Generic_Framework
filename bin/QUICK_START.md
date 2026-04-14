# Quick Start Guide

## 🚀 Run Tests Locally (No Grid)

```bash
# Chrome (default)
mvn clean verify -D"cucumber.filter.tags"="@UISmoke"

# Firefox
mvn clean verify -Denvironment=firefox -D"cucumber.filter.tags"="@UISmoke"

# Headless mode
mvn clean verify -Dheadless.mode=true -D"cucumber.filter.tags"="@UISmoke"
```

## 🌐 Run Tests with Selenium Grid

### Step 1: Start Selenium Grid

**Windows:**
```cmd
start-selenium-grid.bat
```

**Linux/Mac:**
```bash
./start-selenium-grid.sh
```

### Step 2: Run Tests Against Grid

```bash
mvn clean verify \
  -Dwebdriver.remote.url="http://localhost:4444/wd/hub" \
  -D"cucumber.filter.tags"="@UISmoke"
```

### Step 3: View Results

- **Grid Console:** http://localhost:4444/ui
- **Watch Tests Live (noVNC):** http://localhost:7900 (password: secret)
- **Serenity Report:** `target/site/serenity/index.html`

### Step 4: Stop Grid

**Windows:**
```cmd
stop-selenium-grid.bat
```

**Linux/Mac:**
```bash
./stop-selenium-grid.sh
```

## ☁️ Run in Azure Pipeline

1. Push code to repository
2. Create pipeline in Azure DevOps pointing to `azure-pipelines.yml`
3. Run pipeline with parameters:
   - **Tag:** `@UISmoke` (or your tag)
   - **Browser:** `chrome` / `edge` / `firefox`
   - **Headless:** `true` / `false`
   - **Use Remote WebDriver:** `false` (for local) or `true` (for Grid)

## 📊 View Reports

**Local:**
```bash
# Open Serenity report
start target/site/serenity/index.html        # Windows
open target/site/serenity/index.html         # Mac
xdg-open target/site/serenity/index.html     # Linux
```

**Azure Pipeline:**
- View in **Pipeline Summary** (attached report)
- Download from **Artifacts** → `serenity-report`

## 🔧 Common Commands

```bash
# Run specific feature
mvn clean verify -D"cucumber.filter.tags"="@CreateMeal"

# Run all tests
mvn clean verify

# Skip tests (compile only)
mvn clean compile -DskipTests

# Update dependencies
mvn versions:display-dependency-updates

# Clean everything
mvn clean
docker-compose -f docker-compose-selenium-grid.yml down -v
```

## 🐛 Troubleshooting

**Tests fail locally but pass in Grid?**
- Check browser versions match
- Increase timeouts in `serenity.conf`

**Grid won't start?**
- Ensure Docker Desktop is running
- Check port 4444 is not in use: `netstat -ano | findstr :4444`
- Check Docker logs: `docker-compose -f docker-compose-selenium-grid.yml logs`

**Chrome crashes?**
- Use headless mode: `-Dheadless.mode=true`
- Increase shared memory in docker-compose: `shm_size: 4gb`

**Can't connect to Grid?**
- Verify Grid is running: `curl http://localhost:4444/wd/hub/status`
- Check Grid Console: http://localhost:4444/ui

## 📚 Documentation

- **[SELENIUM_GRID_SETUP.md](SELENIUM_GRID_SETUP.md)** - Complete Grid setup guide
- **[AZURE_PIPELINE_SETUP.md](AZURE_PIPELINE_SETUP.md)** - Pipeline configuration guide
- **[README.md](README.md)** - Project overview

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

## 💡 Pro Tips

1. **Use Grid for parallel testing** - Much faster than sequential local runs
2. **Watch tests with noVNC** - Great for debugging failures
3. **Use headless in CI** - More stable and faster
4. **Tag your tests** - Run only what you need
5. **Check Grid Console** - Monitor session distribution and capacity
