# Custom Task Types

This example demonstrates how to define a custom Gradle task type by extending a built-in task type.

The example extends Gradle's built-in `Zip` task type to create a reusable `PackageArchive` task type. The custom task type defines common configuration for ZIP archives, while individual tasks provide their own input directories and archive names.

## Project Structure

```text
task_types/
├── build.gradle
├── README.md
└── src/
    └── main/
        ├── docs/
        │   └── README.md
        └── resources/
            └── application.properties
````

## Custom Task Type

The `build.gradle` file defines the `PackageArchive` task type by extending Gradle's built-in `Zip` task type:

```groovy
abstract class PackageArchive extends Zip {

    PackageArchive() {
        archiveBaseName.convention('resources')
        destinationDirectory.convention(
            project.layout.buildDirectory.dir('packages')
        )
    }
}
```

The custom task type defines default values for the archive name and destination directory.

These defaults can be overridden when registering individual tasks:

```groovy
tasks.register('packageResources', PackageArchive) {
    archiveBaseName.set('app-resources')
    from('src/main/resources')
}

tasks.register('packageDocs', PackageArchive) {
    archiveBaseName.set('documentation')
    from('src/main/docs')
}
```

Both tasks reuse the same task type but package different input directories.

## Running the Example

List the available tasks:

```bash
./gradlew tasks
```

Package the application resources:

```bash
./gradlew packageResources
```

The generated archive is:

```text
build/packages/app-resources.zip
```

Package the documentation:

```bash
./gradlew packageDocs
```

The generated archive is:

```text
build/packages/documentation.zip
```

Both archives are created in the `build/packages` directory.

## Inspecting the Archives

The contents of the generated archives can be inspected with:

```bash
unzip -l build/packages/app-resources.zip
```

and:

```bash
unzip -l build/packages/documentation.zip
```

The `app-resources.zip` archive contains the files from `src/main/resources`, while the `documentation.zip` archive contains the files from `src/main/docs`.

## Key Concepts

This example demonstrates:

* Extending a built-in Gradle task type
* Reusing the behaviour of the `Zip` task type
* Encapsulating common task configuration
* Defining default values with `convention()`
* Overriding task properties with `set()`
* Registering multiple tasks from the same custom task type

The main idea is that a custom task type can encapsulate common configuration and behaviour and then be reused to create multiple tasks.

