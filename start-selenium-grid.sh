#!/bin/bash
# Start Selenium Grid - Helper Script
# Usage: ./start-selenium-grid.sh [standalone|grid|scaled]

set -e

MODE="${1:-grid}"

echo "=========================================="
echo "  Selenium Grid Startup Script"
echo "=========================================="
echo ""

case "$MODE" in
  standalone)
    echo "Starting Standalone Chrome..."
    docker-compose -f docker-compose-selenium-standalone.yml up -d chrome
    echo ""
    echo "✅ Standalone Chrome started!"
    echo "   Selenium: http://localhost:4444"
    echo "   noVNC: http://localhost:7900 (password: secret)"
    echo ""
    echo "Run tests with:"
    echo "   mvn clean verify -Dwebdriver.remote.url=\"http://localhost:4444/wd/hub\""
    ;;
    
  grid)
    echo "Starting Selenium Grid (Hub + Nodes)..."
    docker-compose -f docker-compose-selenium-grid.yml up -d
    echo ""
    echo "Waiting for Grid to be ready..."
    sleep 5
    
    # Wait for hub to be ready
    for i in {1..30}; do
      if curl -s http://localhost:4444/wd/hub/status > /dev/null 2>&1; then
        echo ""
        echo "✅ Selenium Grid is ready!"
        echo "   Grid Console: http://localhost:4444/ui"
        echo "   Grid Status: http://localhost:4444/wd/hub/status"
        echo "   Chrome noVNC: http://localhost:7900"
        echo "   Firefox noVNC: http://localhost:7901"
        echo "   Edge noVNC: http://localhost:7902"
        echo ""
        echo "Run tests with:"
        echo "   mvn clean verify -Dwebdriver.remote.url=\"http://localhost:4444/wd/hub\""
        exit 0
      fi
      echo -n "."
      sleep 2
    done
    
    echo ""
    echo "⚠️  Grid took too long to start. Check logs:"
    echo "   docker-compose -f docker-compose-selenium-grid.yml logs"
    ;;
    
  scaled)
    echo "Starting Scaled Selenium Grid..."
    CHROME_NODES="${CHROME_NODES:-2}"
    FIREFOX_NODES="${FIREFOX_NODES:-1}"
    
    echo "   Chrome nodes: $CHROME_NODES"
    echo "   Firefox nodes: $FIREFOX_NODES"
    
    docker-compose -f docker-compose-selenium-scaled.yml up -d \
      --scale chrome-node=$CHROME_NODES \
      --scale firefox-node=$FIREFOX_NODES
    
    echo ""
    echo "Waiting for Grid to be ready..."
    sleep 5
    
    for i in {1..30}; do
      if curl -s http://localhost:4444/wd/hub/status > /dev/null 2>&1; then
        echo ""
        echo "✅ Scaled Selenium Grid is ready!"
        echo "   Grid Console: http://localhost:4444/ui"
        echo "   Chrome nodes: $CHROME_NODES"
        echo "   Firefox nodes: $FIREFOX_NODES"
        echo ""
        echo "Scale up/down with:"
        echo "   CHROME_NODES=5 ./start-selenium-grid.sh scaled"
        exit 0
      fi
      echo -n "."
      sleep 2
    done
    
    echo ""
    echo "⚠️  Grid took too long to start. Check logs."
    ;;
    
  *)
    echo "❌ Invalid mode: $MODE"
    echo ""
    echo "Usage: $0 [standalone|grid|scaled]"
    echo ""
    echo "Modes:"
    echo "  standalone - Single Chrome container (simplest)"
    echo "  grid       - Full Grid with Hub + all browser nodes (default)"
    echo "  scaled     - Grid with scalable nodes (set CHROME_NODES, FIREFOX_NODES)"
    echo ""
    echo "Examples:"
    echo "  $0                              # Start full grid"
    echo "  $0 standalone                   # Start standalone Chrome"
    echo "  CHROME_NODES=5 $0 scaled        # Start scaled grid with 5 Chrome nodes"
    exit 1
    ;;
esac
