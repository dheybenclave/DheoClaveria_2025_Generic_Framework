# ✅ Selenium Grid Setup - COMPLETE

## 🎉 What Has Been Set Up

Your test automation framework now has **complete Selenium Grid integration** with the following components:

### 1. Docker Compose Configurations (3 modes)

| File | Purpose | Best For |
|------|---------|----------|
| `docker-compose-selenium-standalone.yml` | Individual browser containers | Local development, quick testing |
| `docker-compose-selenium-grid.yml` | Hub + Node architecture | CI/CD, multi-browser testing |
| `docker-compose-selenium-scaled.yml` | Scalable Grid | High-volume, parallel execution |

### 2. Helper Scripts

| Windows | Linux/Mac | Purpose |
|---------|-----------|---------|
| `start-selenium-grid.bat` | `start-selenium-grid.sh` | Start Grid with one command |
| `stop-selenium-grid.bat` | `stop-selenium-grid.sh` | Stop Grid cleanly |
| `verify-setup.bat` | `verify-setup.sh` | Verify all prerequisites |

### 3. Configuration Updates

**serenity.conf** - Added RemoteWebDriver support:
```hocon
environments {
  chrome {
    webdriver {
      remote.url = ${?webdriver.remote.url}  # Conditional RemoteWebDriver
      ...
    }
  }
}
```

**azure-pipelines.yml** - Added Grid support:
- Parameter: `useRemoteDriver` (true/false)
- Parameter: `remoteDriverUrl` (Grid endpoint)
- Dual Maven tasks (local vs remote)

### 4. Documentation Suite

| Document | Content |
|----------|---------|
| `QUICK_START.md` | Quick reference for common tasks |
| `SELENIUM_GRID_SETUP.md` | Complete Grid setup guide (detailed) |
| `AZURE_PIPELINE_SETUP.md` | Pipeline deployment guide |
| `SETUP_SUMMARY.md` | Overview of all changes |
| `README.md` | Updated project overview |

## 🚀 How to Use (Step-by-Step)

### Option A: Local Testing (No Grid)

```bash
# Just run tests normally
mvn clean verify -D"cucumber.filter.tags"="@UISmoke"
```

### Option B: Local Testing with Grid

```bash
# Step 1: Start Grid (Windows)
start-selenium-grid.bat

# Step 1: Start Grid (Linux/Mac)
./start-selenium-grid.sh

# Step 2: Run tests against Grid
mvn clean verify \
  -Dwebdriver.remote.url="http://localhost:4444/wd/hub" \
  -D"cucumber.filter.tags"="@UISmoke"

# Step 3: View live browser (optional)
# Open browser to: http://localhost:7900
# Password: secret

# Step 4: Stop Grid
stop-selenium-grid.bat              # Windows
./stop-selenium-grid.sh             # Linux/Mac
```

### Option C: Azure Pipeline with Grid

**If Grid is external (e.g., separate VM):**
1. Set up Grid on target machine
2. Run pipeline with:
   - `useRemoteDriver: true`
   - `remoteDriverUrl: http://<grid-host>:4444/wd/hub`

**If Grid runs in pipeline (Docker service):**
1. Add Grid service to `azure-pipelines.yml`
2. Set `remoteDriverUrl: http://selenium-hub:4444/wd/hub`

## 📊 Grid Access Points

Once Grid is running:

| Service | URL | Purpose |
|---------|-----|---------|
| Grid Console | http://localhost:4444/ui | Monitor sessions, nodes, capacity |
| Grid Status API | http://localhost:4444/wd/hub/status | JSON status endpoint |
| Chrome noVNC | http://localhost:7900 | Watch Chrome tests live |
| Firefox noVNC | http://localhost:7901 | Watch Firefox tests live |
| Edge noVNC | http://localhost:7902 | Watch Edge tests live |

**noVNC Password:** `secret`

## 🔧 Grid Management Commands

### Start Grid
```bash
# Windows
start-selenium-grid.bat              # Default: Full Grid
start-selenium-grid.bat standalone   # Standalone Chrome only
start-selenium-grid.bat scaled       # Scalable Grid

# Linux/Mac
./start-selenium-grid.sh             # Default: Full Grid
./start-selenium-grid.sh standalone  # Standalone Chrome only
./start-selenium-grid.sh scaled      # Scalable Grid
```

### Stop Grid
```bash
# Windows
stop-selenium-grid.bat               # Stop all
stop-selenium-grid.bat clean         # Stop and remove volumes

# Linux/Mac
./stop-selenium-grid.sh              # Stop all
./stop-selenium-grid.sh clean        # Stop and remove volumes
```

### Check Grid Status
```bash
# API status
curl http://localhost:4444/wd/hub/status

# View in browser
start http://localhost:4444/ui       # Windows
open http://localhost:4444/ui        # Mac
xdg-open http://localhost:4444/ui    # Linux
```

### View Logs
```bash
# All services
docker-compose -f docker-compose-selenium-grid.yml logs

# Specific service
docker-compose -f docker-compose-selenium-grid.yml logs chrome-node

# Follow logs (live)
docker-compose -f docker-compose-selenium-grid.yml logs -f selenium-hub
```

### Scale Nodes
```bash
# Scale Chrome nodes to 5
docker-compose -f docker-compose-selenium-scaled.yml up -d --scale chrome-node=5

# Scale Firefox nodes to 3
docker-compose -f docker-compose-selenium-scaled.yml up -d --scale firefox-node=3

# Check running containers
docker ps | grep selenium
```

## 🎯 Test Execution Examples

### Run All Tests
```bash
mvn clean verify -Dwebdriver.remote.url="http://localhost:4444/wd/hub"
```

### Run Specific Tag
```bash
mvn clean verify \
  -Dwebdriver.remote.url="http://localhost:4444/wd/hub" \
  -D"cucumber.filter.tags"="@Smoke"
```

### Run with Different Browser
```bash
# Chrome (default)
mvn clean verify \
  -Denvironment=chrome \
  -Dwebdriver.remote.url="http://localhost:4444/wd/hub"

# Firefox
mvn clean verify \
  -Denvironment=firefox \
  -Dwebdriver.remote.url="http://localhost:4444/wd/hub"

# Edge
mvn clean verify \
  -Denvironment=edge \
  -Dwebdriver.remote.url="http://localhost:4444/wd/hub"
```

### Run in Headless Mode
```bash
mvn clean verify \
  -Dheadless.mode=true \
  -Dwebdriver.remote.url="http://localhost:4444/wd/hub"
```

### Parallel Execution
```bash
# Maven Failsafe already configured for parallel execution
# Just run normally - Grid will handle multiple sessions
mvn clean verify -Dwebdriver.remote.url="http://localhost:4444/wd/hub"
```

## 🐛 Troubleshooting Guide

### Grid Won't Start

**Check Docker:**
```bash
docker --version
docker ps
```

**Check Port Availability:**
```bash
# Windows
netstat -ano | findstr :4444

# Linux/Mac
lsof -i :4444
```

**View Logs:**
```bash
docker-compose -f docker-compose-selenium-grid.yml logs
```

### Tests Can't Connect

**Verify Grid is Running:**
```bash
curl http://localhost:4444/wd/hub/status
```

**Check Grid Console:**
```
http://localhost:4444/ui
```

**Verify Network:**
```bash
docker network ls
docker network inspect selenium-grid_selenium-grid
```

### Browser Crashes

**Increase Shared Memory:**
Edit docker-compose file:
```yaml
shm_size: 4gb  # Increase from 2gb
```

**Use Headless Mode:**
```bash
-Dheadless.mode=true
```

**Check Resource Limits:**
- Docker Desktop → Settings → Resources
- Increase Memory to 8GB+
- Increase CPUs to 4+

### Session Timeout

**Increase Timeouts in serenity.conf:**
```hocon
webdriver {
  timeouts {
    implicit = 20000
    script = 20000
    pageLoad = 30000
  }
}
```

**Increase Grid Timeouts:**
Edit docker-compose file:
```yaml
environment:
  - SE_NODE_SESSION_TIMEOUT=600  # Increase from 300
```

## 📈 Performance Optimization

### For High-Volume Testing

1. **Use Scaled Grid:**
   ```bash
   CHROME_NODES=10 ./start-selenium-grid.sh scaled
   ```

2. **Increase Node Sessions:**
   ```yaml
   environment:
     - SE_NODE_MAX_SESSIONS=5  # Increase from 3
   ```

3. **Add Resource Limits:**
   ```yaml
   deploy:
     resources:
       limits:
         cpus: '2.0'
         memory: 2G
   ```

### For Faster Execution

1. **Use Headless Mode** - 30-50% faster
2. **Parallel Execution** - Already configured in pom.xml
3. **Reduce Implicit Waits** - Only if tests are stable
4. **Use Standalone for Single Browser** - Less overhead

## 🎓 Best Practices

1. ✅ **Start with Standalone** for local development
2. ✅ **Use Grid for CI/CD** - More reliable and scalable
3. ✅ **Enable noVNC** for debugging - Visual feedback is invaluable
4. ✅ **Monitor Grid Console** - Watch capacity and session distribution
5. ✅ **Use Headless in CI** - Faster and more stable
6. ✅ **Tag Your Tests** - Run only what you need
7. ✅ **Clean Up After Testing** - Stop Grid when not in use
8. ✅ **Version Pin in Production** - Use specific image tags

## 📚 Additional Resources

### Documentation
- [QUICK_START.md](QUICK_START.md) - Quick reference
- [SELENIUM_GRID_SETUP.md](SELENIUM_GRID_SETUP.md) - Detailed setup guide
- [AZURE_PIPELINE_SETUP.md](AZURE_PIPELINE_SETUP.md) - Pipeline guide

### External Links
- Docker Selenium: https://github.com/SeleniumHQ/docker-selenium
- Selenium Grid Docs: https://www.selenium.dev/documentation/grid/
- Serenity BDD: https://serenity-bdd.github.io/

## ✨ Summary

You now have a **production-ready** Selenium Grid setup with:

✅ Three deployment modes (Standalone, Grid, Scaled)  
✅ Helper scripts for easy management  
✅ RemoteWebDriver support in Serenity  
✅ Azure Pipeline integration  
✅ Live browser viewing (noVNC)  
✅ Comprehensive documentation  
✅ Verification scripts  

**Everything is ready to use!** Just run `verify-setup.bat` (or `.sh`) to confirm, then start testing! 🚀

---

**Quick Start:**
```bash
# 1. Verify setup
verify-setup.bat                     # Windows
./verify-setup.sh                    # Linux/Mac

# 2. Start Grid
start-selenium-grid.bat              # Windows
./start-selenium-grid.sh             # Linux/Mac

# 3. Run tests
mvn clean verify -Dwebdriver.remote.url="http://localhost:4444/wd/hub"

# 4. View results
start target/site/serenity/index.html
```

**Need help?** Check the documentation files or run the verification script!
