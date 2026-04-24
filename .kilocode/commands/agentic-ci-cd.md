# Command: agentic-ci-cd

Configure and validate agentic QA execution in GitHub Actions.

## Pipeline Files

- GitHub Actions: `.github/workflows/maven.yml`

## Sequence

1. Validate bootstrap checks are present:
   - collection validation
   - environment check
2. Ensure tag execution is parameterized.
3. Ensure artifact upload/archive is enabled.
4. Run a CI dry-run in PR or manual dispatch.

## Success Criteria

- CI executes init checks before targeted tests
- Artifacts are uploaded even on failure
- Tag-driven execution works for regression slices