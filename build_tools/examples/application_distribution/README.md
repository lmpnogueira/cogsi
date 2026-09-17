# Application Distribution Example

This example demonstrates how Gradle's **Application Plugin** can package a Java application together with its runtime dependencies and launch scripts.

Unlike a regular JAR, an application distribution provides everything needed to run the application from a generated directory or archive.

## Project Structure

```text
application_distribution/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── gradle/
│   └── wrapper/
└── src/
    └── main/
        └── java/
            └── org/
                └── example/
                    └── App.java
````

## Application Plugin

The project applies the `application` plugin:

```groovy
plugins {
    id 'application'
}
```

The main application class is configured with:

```groovy
application {
    mainClass = 'org.example.App'
}
```

The Application Plugin provides tasks for running and packaging the application.

## Dependencies

The application uses an external library:

```groovy
dependencies {
    implementation 'org.apache.commons:commons-lang3:3.18.0'
}
```

This dependency is included in the application's runtime classpath and, consequently, in the generated application distribution.

## Build the Application

Build the project with:

```bash
./gradlew build
```

The application JAR is generated under:

```text
build/libs/
```

The JAR contains the application classes and resources, but does not include the runtime dependencies.

## Run the Application

The Application Plugin provides a `run` task:

```bash
./gradlew run
```

Arguments can be passed to the application with:

```bash
./gradlew run --args="hello gradle"
```

The `run` task uses the configured application main class and the runtime classpath, including the application's dependencies.

## Install the Application

Create an application installation containing the application JAR, runtime dependencies, and launch scripts:

```bash
./gradlew installDist
```

The distribution is created under:

```text
build/install/application_distribution/
```

It contains a structure similar to:

```text
build/install/application_distribution/
├── bin/
│   ├── application_distribution
│   └── application_distribution.bat
└── lib/
    ├── application_distribution.jar
    └── commons-lang3-3.18.0.jar
```

The `bin` directory contains platform-specific launch scripts, while `lib` contains the application JAR and its runtime dependencies.

The application can be launched directly with:

```bash
./build/install/application_distribution/bin/application_distribution
```

Arguments can be passed to the generated launch script:

```bash
./build/install/application_distribution/bin/application_distribution "hello gradle"
```

## Create a ZIP Distribution

Generate a ZIP archive containing the complete application distribution:

```bash
./gradlew distZip
```

The archive is generated under:

```text
build/distributions/
```

The ZIP contains:

* the application JAR;
* runtime dependencies;
* launch scripts;
* the directory structure required to run the application.

The generated archive can be extracted on another compatible system and executed using the included launch script.

## Create a TAR Distribution

A TAR distribution can be generated with:

```bash
./gradlew distTar
```

The resulting archive is also placed under:

```text
build/distributions/
```

The TAR distribution contains the same application components as the ZIP distribution.

## Inspect the Application Tasks

The Application Plugin adds several tasks to the project.

List the available application-related tasks with:

```bash
./gradlew tasks --group application
```

You can inspect the tasks involved in creating the ZIP distribution without executing them:

```bash
./gradlew distZip --dry-run
```

This shows the task graph that Gradle would execute for the requested operation.

## JAR vs Application Distribution

A regular JAR:

```text
application_distribution.jar
```

contains the application classes and resources.

An application distribution:

```text
application_distribution.zip
```

contains:

* the application JAR;
* runtime dependencies;
* launch scripts;
* the directory structure required to run the application.

Therefore, the Application Plugin is useful when the goal is to produce a **distributable application**, rather than only a Java library or a single JAR.

## Standard Application Plugin Tasks

The Application Plugin provides several tasks for working with the application:

| Task          | Purpose                                          |
| ------------- | ------------------------------------------------ |
| `run`         | Runs the application using the runtime classpath |
| `installDist` | Creates an application installation directory    |
| `distZip`     | Creates a ZIP application distribution           |
| `distTar`     | Creates a TAR application distribution           |

These tasks are provided by the plugin, so there is no need to implement custom packaging tasks for these standard distribution formats.

## Key Concepts

This example illustrates:

* the `application` plugin;
* application entry points;
* runtime dependencies;
* application installations;
* launch scripts;
* `run`;
* `installDist`;
* `distZip`;
* `distTar`;
* JAR vs application distribution;
* Gradle task graphs.

The example intentionally uses the standard Application Plugin distribution mechanism rather than defining custom packaging tasks.

This illustrates an important Gradle principle:

> **Before creating a custom task, check whether an appropriate plugin already provides the required functionality.**
