# DheoClaveria Generic Framework 2025

This project has been updated to require Java 21 (LTS).

Notes:
- Maven builds use the maven-compiler-plugin with <release>21</release>.
- Gradle builds use a Java toolchain configured to languageVersion 21 and the Gradle wrapper (8.14) is compatible with Java 21.
- Gradle will auto-download a suitable JDK if `org.gradle.java.installations.auto-download=true` is set in `gradle.properties`.

If you need to run builds locally, install JDK 21 or allow Gradle to auto-download one. On Windows you can run:

```powershell
cd path\to\project; .\gradlew.bat clean assemble
```


