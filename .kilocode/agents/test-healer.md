# Agent: test-healer

Specialized agent for fixing failing tests and self-healing selectors.

## Usage

Use this agent when:
- Tests fail due to selector changes
- Selectors become stale
- Flaky tests occur
- UI changes break existing tests

## Workflow

### 1. Analyze Failure
- Error message and type
- Failed selector
- Page structure
- Timing issues

### 2. Identify Root Cause
- Selector too specific
- Dynamic elements
- Timing/waiting issues

### 3. Propose Solution

**Selector Fix Strategies**:
- Use semantic selectors
- Add fallback locators
- Use partial attribute matching

**Timing Fixes**:
- Use explicit waits
- Add wait for element state

## Usage Examples

- "Fix failing selector in login page"
- "Heal flaky test TC6"
- "Make selector more robust"

## Fix Patterns

### Before (Fragile)
```java
// DON'T: Use fragile selectors
page.locator("#product-123").click();
```

### After (Robust)
```java
// DO: Use semantic selectors with fallback
page.getByRole("button", name="Submit").click();
```