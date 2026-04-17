# Command: verify - Environment Verification

Verify the Serenity BDD Java test environment is properly configured.

## Checks Performed

1. **Maven Configuration**
   - Verify `pom.xml` exists and is valid
   - Check dependencies resolve correctly

2. **Java Version**
   - Verify Java 11+ is installed
   - Check JAVA_HOME is set

3. **Project Structure**
   - Verify `src/test/resources/features/` exists
   - Verify `src/test/java/stepdefinitions/` exists
   - Verify `src/test/java/pages/` exists

4. **Serenity Configuration**
   - Verify `serenity.conf` exists
   - Verify page configurations are defined

## Usage

```bash
# Run verification
mvn --version
mvn dependency:resolve

# Or ask Kilo
kilo "Verify environment setup"
```

## Expected Output

- Maven version information
- Dependency tree resolved
- No build errors