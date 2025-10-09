@echo off
REM Stop Selenium Grid - Windows Helper Script
REM Usage: stop-selenium-grid.bat [standalone|grid|scaled|all|clean]

setlocal

set MODE=%1
if "%MODE%"=="" set MODE=all

echo ==========================================
echo   Selenium Grid Shutdown Script
echo ==========================================
echo.

if "%MODE%"=="standalone" goto STANDALONE
if "%MODE%"=="grid" goto GRID
if "%MODE%"=="scaled" goto SCALED
if "%MODE%"=="all" goto ALL
if "%MODE%"=="clean" goto CLEAN
goto INVALID

:STANDALONE
echo Stopping Standalone containers...
docker-compose -f docker-compose-selenium-standalone.yml down
echo [32m✓ Standalone containers stopped[0m
goto SHOW_REMAINING

:GRID
echo Stopping Selenium Grid...
docker-compose -f docker-compose-selenium-grid.yml down
echo [32m✓ Selenium Grid stopped[0m
goto SHOW_REMAINING

:SCALED
echo Stopping Scaled Grid...
docker-compose -f docker-compose-selenium-scaled.yml down
echo [32m✓ Scaled Grid stopped[0m
goto SHOW_REMAINING

:ALL
echo Stopping all Selenium containers...
docker-compose -f docker-compose-selenium-standalone.yml down 2>nul
docker-compose -f docker-compose-selenium-grid.yml down 2>nul
docker-compose -f docker-compose-selenium-scaled.yml down 2>nul
echo [32m✓ All Selenium containers stopped[0m
goto SHOW_REMAINING

:CLEAN
echo Stopping and cleaning all Selenium containers...
docker-compose -f docker-compose-selenium-standalone.yml down -v 2>nul
docker-compose -f docker-compose-selenium-grid.yml down -v 2>nul
docker-compose -f docker-compose-selenium-scaled.yml down -v 2>nul
echo [32m✓ All containers stopped and volumes removed[0m
goto SHOW_REMAINING

:INVALID
echo [31m✗ Invalid mode: %MODE%[0m
echo.
echo Usage: %0 [standalone^|grid^|scaled^|all^|clean]
echo.
echo Modes:
echo   standalone - Stop standalone containers
echo   grid       - Stop grid containers
echo   scaled     - Stop scaled grid containers
echo   all        - Stop all Selenium containers (default)
echo   clean      - Stop all and remove volumes
exit /b 1

:SHOW_REMAINING
echo.
echo Remaining Selenium containers:
docker ps | findstr selenium
if errorlevel 1 echo   None

endlocal
