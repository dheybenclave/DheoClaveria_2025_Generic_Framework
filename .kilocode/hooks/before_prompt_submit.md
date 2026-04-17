# Before Prompt Submit Hook

Validates prompts for secrets before submission.

## What It Does

Detects sensitive information in prompts:

1. **API Keys**
   - OpenAI keys (`sk-...`)
   - GitHub tokens (`ghp_...`)

2. **Credentials**
   - AWS keys (`AKIA...`)
   - Database passwords
   - Excel password protected files (don't share credentials)

3. **System Paths**
   - Absolute paths (warns about sharing local paths)

## Usage

```bash
# Auto-triggered before prompt submission
# Or manually:
# Just be mindful not to share credentials
```

## Security Guidelines

- Never include real credentials in prompts
- Use role-based Excel lookups instead of hardcoding
- Reference credentials via environment variables