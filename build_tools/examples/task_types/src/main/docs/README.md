# Documentation

This directory contains documentation that is packaged by the `PackageArchive` task type.

Its contents are included in the generated ZIP archive when running:

```bash
./gradlew packageDocs
````

The resulting archive is created at:

```text
build/packages/documentation.zip
```

This directory is intentionally simple. Its purpose is to provide input files for demonstrating how a custom Gradle task type can reuse and specialize the behaviour of the built-in `Zip` task type.

