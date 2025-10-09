@echo off
REM Start Selenium Grid - Windows Helper Script
REM Usage: start-selenium-grid.bat [standalone|grid|scaled]

setlocal enabledelayedexpansion

set MODE=%1
if "%MODE%"=="" set MODE=grid

echo ==========================================
echo   Selenium Grid Startup Script
echo ==========================================
echo.

if "%MODE%"=="standalone" goto STANDALONE
if "%MODE%"=="grid" goto GRID
if "%MODE%"=="scaled" goto SCALED
goto INVALID

:STANDALONE
echo Starting Standalone Chrome...
docker-compose -f docker-compose-selenium-standalone.yml up -d chrome
echo.
echo [32m✓ Standalone Chrome started![0m
echo    Selenium: http://localhost:4444
echo    noVNC: http://localhost:7900 (password: secret)
echo.
echo Run tests with:
echo    mvn clean verify -Dwebdriver.remote.url="http://localhost:4444/wd/hub"
goto END

:GRID
echo Starting Selenium Grid (Hub + Nodes)...
docker-compose -f docker-compose-selenium-grid.yml up -d
echo.
echo Waiting for Grid to be ready...
timeout /t 5 /nobreak >nul

REM Wait for hub to be ready
set /a counter=0
:WAIT_GRID
set /a counter+=1
if %counter% GTR 30 goto TIMEOUT

curl -s http://localhost:4444/wd/hub/status >nul 2>&1
if errorlevel 1 (
    echo|set /p="."
    timeout /t 2 /nobreak >nul
    goto WAIT_GRID
)

echo.
echo [32m✓ Selenium Grid is ready![0m
echo    Grid Console: http://localhost:4444/ui
echo    Grid Status: http://localhost:4444/wd/hub/status
echo    Chrome noVNC: http://localhost:7900
echo    Firefox noVNC: http://localhost:7901
echo    Edge noVNC: http://localhost:7902
echo.
echo Run tests with:
echo    mvn clean verify -Dwebdriver.remote.url="http://localhost:4444/wd/hub"
goto END

:SCALED
echo Starting Scaled Selenium Grid...
if "%CHROME_NODES%"=="" set CHROME_NODES=2
if "%FIREFOX_NODES%"=="" set FIREFOX_NODES=1

echo    Chrome nodes: %CHROME_NODES%
echo    Firefox nodes: %FIREFOX_NODES%

docker-compose -f docker-compose-selenium-scaled.yml up -d --scale chrome-node=%CHROME_NODES% --scale firefox-node=%FIREFOX_NODES%

echo.
echo Waiting for Grid to be ready...
timeout /t 5 /nobreak >nul

set /a counter=0
:WAIT_SCALED
set /a counter+=1
if %counter% GTR 30 goto TIMEOUT

curl -s http://localhost:4444/wd/hub/status >nul 2>&1
if errorlevel 1 (
    echo|set /p="."
    timeout /t 2 /nobreak >nul
    goto WAIT_SCALED
)

echo.
echo [32m✓ Scaled Selenium Grid is ready![0m
echo    Grid Console: http://localhost:4444/ui
echo    Chrome nodes: %CHROME_NODES%
echo    Firefox nodes: %FIREFOX_NODES%
echo.
echo Scale up/down with:
echo    set CHROME_NODES=5 ^&^& start-selenium-grid.bat scaled
goto END

:TIMEOUT
echo.
echo [33m⚠ Grid took too long to start. Check logs:[0m
echo    docker-compose -f docker-compose-selenium-grid.yml logs
goto END

:INVALID
echo [31m✗ Invalid mode: %MODE%[0m
echo.
echo Usage: %0 [standalone^|grid^|scaled]
echo.
echo Modes:
echo   standalone - Single Chrome container (simplest)
echo   grid       - Full Grid with Hub + all browser nodes (default)
echo   scaled     - Grid with scalable nodes (set CHROME_NODES, FIREFOX_NODES)
echo.
echo Examples:
echo   %0                                    # Start full grid
echo   %0 standalone                         # Start standalone Chrome
echo   set CHROME_NODES=5 ^&^& %0 scaled    # Start scaled grid with 5 Chrome nodes
exit /b 1

:END
endlocal
