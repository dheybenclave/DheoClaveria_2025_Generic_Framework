# Skill: init

Environment initialization and validation skill.

## Description

Validates local environment setup and test readiness.

## Scripts

### verify_env.py

Verifies environment:
- Java/Maven installed
- Dependencies resolved
- Project structure valid

### smoke_collect.py

Quick test collection:
- Scans feature files
- Lists available scenarios
- Validates step definitions

## Usage

```bash
# Verify environment
python .kilocode/skills/init/scripts/verify_env.py

# Quick smoke collection
python .kilocode/skills/init/scripts/smoke_collect.py
```

## Requirements

- Maven 3.8+
- Java 11+
- `mvn dependency:resolve` passes