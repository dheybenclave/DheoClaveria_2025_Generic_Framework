@echo off
REM Verification script for Selenium Grid setup
REM This script checks if all components are properly configured

echo ==========================================
echo   Setup Verification Script
echo ==========================================
echo.

set ERRORS=0

REM Check Docker
echo [1/7] Checking Docker...
docker --version >nul 2>&1
if errorlevel 1 (
    echo [31m✗ Docker not found. Please install Docker Desktop.[0m
    set /a ERRORS+=1
) else (
    echo [32m✓ Docker is installed[0m
)

REM Check Docker is running
echo [2/7] Checking if Docker is running...
docker ps >nul 2>&1
if errorlevel 1 (
    echo [31m✗ Docker is not running. Please start Docker Desktop.[0m
    set /a ERRORS+=1
) else (
    echo [32m✓ Docker is running[0m
)

REM Check Maven
echo [3/7] Checking Maven...
mvn --version >nul 2>&1
if errorlevel 1 (
    echo [31m✗ Maven not found. Please install Maven.[0m
    set /a ERRORS+=1
) else (
    echo [32m✓ Maven is installed[0m
)

REM Check Java
echo [4/7] Checking Java...
java -version >nul 2>&1
if errorlevel 1 (
    echo [31m✗ Java not found. Please install JDK 21.[0m
    set /a ERRORS+=1
) else (
    echo [32m✓ Java is installed[0m
)

REM Check Docker Compose files
echo [5/7] Checking Docker Compose files...
if not exist "docker-compose-selenium-standalone.yml" (
    echo [31m✗ docker-compose-selenium-standalone.yml not found[0m
    set /a ERRORS+=1
) else if not exist "docker-compose-selenium-grid.yml" (
    echo [31m✗ docker-compose-selenium-grid.yml not found[0m
    set /a ERRORS+=1
) else if not exist "docker-compose-selenium-scaled.yml" (
    echo [31m✗ docker-compose-selenium-scaled.yml not found[0m
    set /a ERRORS+=1
) else (
    echo [32m✓ All Docker Compose files present[0m
)

REM Check helper scripts
echo [6/7] Checking helper scripts...
if not exist "start-selenium-grid.bat" (
    echo [31m✗ start-selenium-grid.bat not found[0m
    set /a ERRORS+=1
) else if not exist "stop-selenium-grid.bat" (
    echo [31m✗ stop-selenium-grid.bat not found[0m
    set /a ERRORS+=1
) else (
    echo [32m✓ Helper scripts present[0m
)

REM Check Azure Pipeline file
echo [7/7] Checking Azure Pipeline configuration...
if not exist "azure-pipelines.yml" (
    echo [31m✗ azure-pipelines.yml not found[0m
    set /a ERRORS+=1
) else (
    echo [32m✓ Azure Pipeline configuration present[0m
)

echo.
echo ==========================================
if %ERRORS%==0 (
    echo [32m✓ All checks passed! Setup is complete.[0m
    echo.
    echo Next steps:
    echo   1. Start Selenium Grid: start-selenium-grid.bat
    echo   2. Run tests: mvn clean verify -Dwebdriver.remote.url="http://localhost:4444/wd/hub"
    echo   3. View Grid Console: http://localhost:4444/ui
    echo.
    echo For more information, see:
    echo   - QUICK_START.md
    echo   - SELENIUM_GRID_SETUP.md
    echo   - AZURE_PIPELINE_SETUP.md
) else (
    echo [31m✗ %ERRORS% check(s) failed. Please fix the issues above.[0m
    exit /b 1
)
echo ==========================================
