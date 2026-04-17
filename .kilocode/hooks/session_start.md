# Session Start Hook

Shows Kilo QA agentic startup checklist when session starts.

## What It Does

1. **Environment Check**
   - Verify Maven is available
   - Verify Java version

2. **Project Structure Check**
   - Verify `pom.xml` exists
   - Verify `src/test/resources/features/` exists

3. **Quick Commands**
   - List available test tags
   - Show recent test results

## Usage

```bash
# Auto-run on session start
python .kilocode/hooks/session_start.py
```

Or ask Kilo: "Start session" or "Begin work"