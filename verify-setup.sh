#!/bin/bash
# Verification script for Selenium Grid setup
# This script checks if all components are properly configured

set -e

echo "=========================================="
echo "  Setup Verification Script"
echo "=========================================="
echo ""

ERRORS=0

# Check Docker
echo "[1/7] Checking Docker..."
if command -v docker &> /dev/null; then
    echo "✓ Docker is installed"
else
    echo "✗ Docker not found. Please install Docker Desktop."
    ((ERRORS++))
fi

# Check Docker is running
echo "[2/7] Checking if Docker is running..."
if docker ps &> /dev/null; then
    echo "✓ Docker is running"
else
    echo "✗ Docker is not running. Please start Docker Desktop."
    ((ERRORS++))
fi

# Check Maven
echo "[3/7] Checking Maven..."
if command -v mvn &> /dev/null; then
    echo "✓ Maven is installed"
else
    echo "✗ Maven not found. Please install Maven."
    ((ERRORS++))
fi

# Check Java
echo "[4/7] Checking Java..."
if command -v java &> /dev/null; then
    echo "✓ Java is installed"
else
    echo "✗ Java not found. Please install JDK 21."
    ((ERRORS++))
fi

# Check Docker Compose files
echo "[5/7] Checking Docker Compose files..."
if [[ ! -f "docker-compose-selenium-standalone.yml" ]]; then
    echo "✗ docker-compose-selenium-standalone.yml not found"
    ((ERRORS++))
elif [[ ! -f "docker-compose-selenium-grid.yml" ]]; then
    echo "✗ docker-compose-selenium-grid.yml not found"
    ((ERRORS++))
elif [[ ! -f "docker-compose-selenium-scaled.yml" ]]; then
    echo "✗ docker-compose-selenium-scaled.yml not found"
    ((ERRORS++))
else
    echo "✓ All Docker Compose files present"
fi

# Check helper scripts
echo "[6/7] Checking helper scripts..."
if [[ ! -f "start-selenium-grid.sh" ]]; then
    echo "✗ start-selenium-grid.sh not found"
    ((ERRORS++))
elif [[ ! -f "stop-selenium-grid.sh" ]]; then
    echo "✗ stop-selenium-grid.sh not found"
    ((ERRORS++))
else
    echo "✓ Helper scripts present"
fi

# Check Azure Pipeline file
echo "[7/7] Checking Azure Pipeline configuration..."
if [[ ! -f "azure-pipelines.yml" ]]; then
    echo "✗ azure-pipelines.yml not found"
    ((ERRORS++))
else
    echo "✓ Azure Pipeline configuration present"
fi

echo ""
echo "=========================================="
if [ $ERRORS -eq 0 ]; then
    echo "✓ All checks passed! Setup is complete."
    echo ""
    echo "Next steps:"
    echo "  1. Start Selenium Grid: ./start-selenium-grid.sh"
    echo "  2. Run tests: mvn clean verify -Dwebdriver.remote.url=\"http://localhost:4444/wd/hub\""
    echo "  3. View Grid Console: http://localhost:4444/ui"
    echo ""
    echo "For more information, see:"
    echo "  - QUICK_START.md"
    echo "  - SELENIUM_GRID_SETUP.md"
    echo "  - AZURE_PIPELINE_SETUP.md"
else
    echo "✗ $ERRORS check(s) failed. Please fix the issues above."
    exit 1
fi
echo "=========================================="
