#!/bin/bash
# Stop Selenium Grid - Helper Script
# Usage: ./stop-selenium-grid.sh [standalone|grid|scaled|all]

set -e

MODE="${1:-all}"

echo "=========================================="
echo "  Selenium Grid Shutdown Script"
echo "=========================================="
echo ""

case "$MODE" in
  standalone)
    echo "Stopping Standalone containers..."
    docker-compose -f docker-compose-selenium-standalone.yml down
    echo "✅ Standalone containers stopped"
    ;;
    
  grid)
    echo "Stopping Selenium Grid..."
    docker-compose -f docker-compose-selenium-grid.yml down
    echo "✅ Selenium Grid stopped"
    ;;
    
  scaled)
    echo "Stopping Scaled Grid..."
    docker-compose -f docker-compose-selenium-scaled.yml down
    echo "✅ Scaled Grid stopped"
    ;;
    
  all)
    echo "Stopping all Selenium containers..."
    docker-compose -f docker-compose-selenium-standalone.yml down 2>/dev/null || true
    docker-compose -f docker-compose-selenium-grid.yml down 2>/dev/null || true
    docker-compose -f docker-compose-selenium-scaled.yml down 2>/dev/null || true
    echo "✅ All Selenium containers stopped"
    ;;
    
  clean)
    echo "Stopping and cleaning all Selenium containers..."
    docker-compose -f docker-compose-selenium-standalone.yml down -v 2>/dev/null || true
    docker-compose -f docker-compose-selenium-grid.yml down -v 2>/dev/null || true
    docker-compose -f docker-compose-selenium-scaled.yml down -v 2>/dev/null || true
    echo "✅ All containers stopped and volumes removed"
    ;;
    
  *)
    echo "❌ Invalid mode: $MODE"
    echo ""
    echo "Usage: $0 [standalone|grid|scaled|all|clean]"
    echo ""
    echo "Modes:"
    echo "  standalone - Stop standalone containers"
    echo "  grid       - Stop grid containers"
    echo "  scaled     - Stop scaled grid containers"
    echo "  all        - Stop all Selenium containers (default)"
    echo "  clean      - Stop all and remove volumes"
    exit 1
    ;;
esac

echo ""
echo "Remaining Selenium containers:"
docker ps | grep selenium || echo "  None"
