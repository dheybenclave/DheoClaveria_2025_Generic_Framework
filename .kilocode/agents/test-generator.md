# Agent: test-generator

Specialized agent for generating test cases from requirements.

## Usage

Use this agent when:
- Generate new test coverage
- Create scenarios from user stories
- Add regression test cases
- Expand test suite

## Workflow

### 1. Analyze Requirements
- Review user story or feature description
- Identify acceptance criteria
- Determine test data needs

### 2. Design Scenarios
- Happy path scenarios
- Edge case scenarios
- Negative scenarios
- Data-driven scenarios

### 3. Implement Tests
- Create feature file with scenarios
- Add step definitions if needed
- Update page objects if needed

### 4. Validate
- Run `mvn verify` to validate
- Check report artifacts

## Usage Examples

- "Generate login test cases"
- "Create test cases for checkout flow"
- "Add negative test cases for registration"

## Test Generation Patterns

```gherkin
Feature: Login Functionality

  Scenario: Valid login
    Given I am on the login page
    When I enter valid credentials
    Then I should see the dashboard
```

## Integration

Works with:
- `feature-development` command
- Page objects in `src/test/java/pages/`
- Step definitions in `src/test/java/stepdefinitions/`