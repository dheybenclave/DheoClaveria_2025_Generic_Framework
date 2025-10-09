# Selenium Grid Setup Guide

## Overview

This project includes three Docker Compose configurations for Selenium Grid:

1. **Standalone Mode** - Simple single-browser containers (recommended for local development)
2. **Hub-Node Mode** - Full Grid with hub and multiple browser nodes (recommended for CI/CD)
3. **Scaled Mode** - Hub with dynamically scalable nodes (recommended for high-volume testing)

## Prerequisites

- **Docker Desktop** installed and running
  - Windows: https://docs.docker.com/desktop/install/windows-install/
  - Mac: https://docs.docker.com/desktop/install/mac-install/
  - Linux: https://docs.docker.com/desktop/install/linux-install/
- **Docker Compose** (included with Docker Desktop)
- Minimum 4GB RAM allocated to Docker

## Quick Start

### Option 1: Standalone Chrome (Simplest)

Best for: Local development, single browser testing

```bash
# Start standalone Chrome
docker-compose -f docker-compose-selenium-standalone.yml up -d chrome

# Verify it's running
curl http://localhost:4444/wd/hub/status

# Run tests against it
mvn clean verify -Denvironment=chrome -Dwebdriver.remote.url="http://localhost:4444/wd/hub"

# Stop when done
docker-compose -f docker-compose-selenium-standalone.yml down
```

**Access Points:**
- Selenium: http://localhost:4444
- noVNC (view browser): http://localhost:7900 (password: secret)

### Option 2: Full Grid (Recommended for CI/CD)

Best for: Multi-browser testing, parallel execution

```bash
# Start the entire grid (hub + all browser nodes)
docker-compose -f docker-compose-selenium-grid.yml up -d

# Check grid status
curl http://localhost:4444/wd/hub/status

# View Grid Console
# Open browser: http://localhost:4444/ui

# Run tests
mvn clean verify -Denvironment=chrome -Dwebdriver.remote.url="http://localhost:4444/wd/hub"

# Stop the grid
docker-compose -f docker-compose-selenium-grid.yml down
```

**Access Points:**
- Grid Console: http://localhost:4444/ui
- Grid Status: http://localhost:4444/wd/hub/status
- Chrome noVNC: http://localhost:7900
- Firefox noVNC: http://localhost:7901
- Edge noVNC: http://localhost:7902

### Option 3: Scaled Grid (High Volume)

Best for: Load testing, large test suites, parallel execution

```bash
# Start with default replicas
docker-compose -f docker-compose-selenium-scaled.yml up -d

# Scale Chrome nodes to 5
docker-compose -f docker-compose-selenium-scaled.yml up -d --scale chrome-node=5

# Scale Firefox nodes to 3
docker-compose -f docker-compose-selenium-scaled.yml up -d --scale firefox-node=3

# Check how many nodes are running
docker ps | grep selenium

# Stop the grid
docker-compose -f docker-compose-selenium-scaled.yml down
```

## Configuration Details

### Standalone Mode (docker-compose-selenium-standalone.yml)

**Services:**
- `chrome` - Standalone Chrome on port 4444
- `firefox` - Standalone Firefox on port 4445
- `edge` - Standalone Edge on port 4446

**Features:**
- Each browser runs independently
- 5 max sessions per container
- 300 second session timeout
- noVNC enabled for visual debugging

**Usage:**
```bash
# Start only Chrome
docker-compose -f docker-compose-selenium-standalone.yml up -d chrome

# Start all browsers
docker-compose -f docker-compose-selenium-standalone.yml up -d

# Connect to specific browser
-Dwebdriver.remote.url="http://localhost:4444/wd/hub"  # Chrome
-Dwebdriver.remote.url="http://localhost:4445/wd/hub"  # Firefox
-Dwebdriver.remote.url="http://localhost:4446/wd/hub"  # Edge
```

### Hub-Node Mode (docker-compose-selenium-grid.yml)

**Services:**
- `selenium-hub` - Central hub on port 4444
- `chrome-node` - Chrome node (3 max sessions)
- `firefox-node` - Firefox node (3 max sessions)
- `edge-node` - Edge node (3 max sessions)

**Features:**
- Single endpoint for all browsers
- Automatic browser routing
- Health checks enabled
- Event bus for node communication

**Usage:**
```bash
# Start the grid
docker-compose -f docker-compose-selenium-grid.yml up -d

# All tests connect to same URL
-Dwebdriver.remote.url="http://localhost:4444/wd/hub"

# Grid automatically routes to correct browser based on capabilities
```

### Scaled Mode (docker-compose-selenium-scaled.yml)

**Services:**
- `selenium-hub` - Central hub (supports 20 max sessions)
- `chrome-node` - Scalable Chrome nodes (2 sessions each)
- `firefox-node` - Scalable Firefox nodes (2 sessions each)

**Features:**
- Dynamic scaling
- Higher session limits
- Optimized for parallel execution

**Usage:**
```bash
# Start with defaults (2 Chrome, 1 Firefox)
docker-compose -f docker-compose-selenium-scaled.yml up -d

# Scale up for heavy load
docker-compose -f docker-compose-selenium-scaled.yml up -d --scale chrome-node=10

# Scale down
docker-compose -f docker-compose-selenium-scaled.yml up -d --scale chrome-node=2
```

## Integration with Tests

### Local Maven Execution

```bash
# With Selenium Grid running
mvn clean verify \
  -Denvironment=chrome \
  -Dwebdriver.remote.url="http://localhost:4444/wd/hub" \
  -D"cucumber.filter.tags"="@UISmoke"
```

### Azure Pipeline Integration

In `azure-pipelines.yml`, set parameters:
- `useRemoteDriver: true`
- `remoteDriverUrl: http://selenium-hub:4444/wd/hub`

**Note:** If Grid runs in a separate container/service in Azure, use the service name as hostname.

### Docker Network Integration

If running tests inside Docker alongside Grid:

```yaml
# Add to your test container
networks:
  - selenium-grid

# Use hub service name
  - -Dwebdriver.remote.url="http://selenium-hub:4444/wd/hub"
```

## Viewing Live Browser Sessions

All configurations include noVNC for visual debugging:

1. **Open browser to noVNC URL** (see Access Points above)
2. **Password:** `secret` (default)
3. **Watch tests execute in real-time**

This is invaluable for debugging test failures!

## Monitoring and Management

### Check Grid Status

```bash
# JSON status
curl http://localhost:4444/wd/hub/status | jq

# Grid Console (browser)
open http://localhost:4444/ui
```

### View Logs

```bash
# All services
docker-compose -f docker-compose-selenium-grid.yml logs

# Specific service
docker-compose -f docker-compose-selenium-grid.yml logs chrome-node

# Follow logs
docker-compose -f docker-compose-selenium-grid.yml logs -f selenium-hub
```

### Container Management

```bash
# List running containers
docker ps

# Stop specific service
docker-compose -f docker-compose-selenium-grid.yml stop chrome-node

# Restart service
docker-compose -f docker-compose-selenium-grid.yml restart chrome-node

# Remove all containers and networks
docker-compose -f docker-compose-selenium-grid.yml down -v
```

## Performance Tuning

### Increase Shared Memory

If you see browser crashes, increase `shm_size`:

```yaml
shm_size: 4gb  # Default is 2gb
```

### Adjust Session Limits

For more parallel sessions per node:

```yaml
environment:
  - SE_NODE_MAX_SESSIONS=5  # Increase from 3
```

### Timeout Configuration

```yaml
environment:
  - SE_NODE_SESSION_TIMEOUT=600  # Increase from 300 seconds
  - SE_SESSION_REQUEST_TIMEOUT=600
```

### Resource Limits

Add resource constraints:

```yaml
deploy:
  resources:
    limits:
      cpus: '2.0'
      memory: 2G
    reservations:
      cpus: '1.0'
      memory: 1G
```

## Troubleshooting

### Issue: Container won't start

**Check Docker resources:**
```bash
docker system df
docker system prune  # Clean up unused resources
```

**Check logs:**
```bash
docker-compose -f docker-compose-selenium-grid.yml logs selenium-hub
```

### Issue: Tests can't connect to Grid

**Verify Grid is running:**
```bash
curl http://localhost:4444/wd/hub/status
```

**Check network connectivity:**
```bash
docker network ls
docker network inspect selenium-grid_selenium-grid
```

**Verify port is not in use:**
```bash
# Windows
netstat -ano | findstr :4444

# Linux/Mac
lsof -i :4444
```

### Issue: Browser sessions hang

**Increase timeouts in serenity.conf:**
```hocon
webdriver {
  timeouts {
    implicit = 20000
    script = 20000
    pageLoad = 30000
  }
}
```

**Check node capacity:**
```bash
# View Grid Console
open http://localhost:4444/ui
```

### Issue: Out of memory errors

**Increase Docker memory:**
- Docker Desktop → Settings → Resources → Memory → 8GB+

**Reduce parallel sessions:**
```yaml
environment:
  - SE_NODE_MAX_SESSIONS=2  # Reduce from 3
```

## Best Practices

1. **Use Hub-Node for CI/CD** - More reliable than standalone
2. **Enable noVNC for debugging** - Visual feedback is invaluable
3. **Monitor Grid Console** - Watch session distribution
4. **Set appropriate timeouts** - Balance speed vs stability
5. **Clean up regularly** - `docker-compose down` after testing
6. **Use health checks** - Ensure hub is ready before tests
7. **Scale based on load** - Start small, scale up as needed
8. **Version pin images** - Use specific tags in production (e.g., `selenium/hub:4.16.0`)

## Advanced: Custom Browser Images

Create custom Dockerfile with additional tools:

```dockerfile
FROM selenium/node-chrome:latest

USER root

# Install additional tools
RUN apt-get update && apt-get install -y \
    curl \
    jq \
    && rm -rf /var/lib/apt/lists/*

USER seluser
```

Build and use:
```bash
docker build -t custom-chrome-node .
# Update docker-compose.yml to use custom-chrome-node image
```

## Cleanup

```bash
# Stop and remove all containers
docker-compose -f docker-compose-selenium-grid.yml down

# Remove volumes too
docker-compose -f docker-compose-selenium-grid.yml down -v

# Remove all Selenium images (optional)
docker images | grep selenium | awk '{print $3}' | xargs docker rmi
```

## Next Steps

1. **Start with Standalone Chrome** for local development
2. **Test RemoteWebDriver connection** with a simple test
3. **Move to Hub-Node** when ready for multi-browser testing
4. **Integrate with Azure Pipeline** using the provided configuration
5. **Scale as needed** for larger test suites

## Support Resources

- Selenium Grid Documentation: https://www.selenium.dev/documentation/grid/
- Docker Selenium GitHub: https://github.com/SeleniumHQ/docker-selenium
- Serenity BDD RemoteDriver: https://serenity-bdd.github.io/docs/guide/driver_config
