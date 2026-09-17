# Publishing Example

This example demonstrates how Gradle can publish a Java library to a Maven repository.

The project uses the `java-library` and `maven-publish` plugins and publishes the Java component to the local Maven repository.

## Project Structure

```text
publishing/
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
                    └── Greeting.java
```

## Plugins

The project applies two plugins:

* `java-library` — provides the Java library conventions and tasks.
* `maven-publish` — provides support for publishing artifacts to Maven repositories.

```groovy
plugins {
    id 'java-library'
    id 'maven-publish'
}
```

## Coordinates

The library is identified by the following Maven coordinates:

```groovy
group = 'org.example'
version = '1.0.0'
```

The artifact name is derived from the project name.

The resulting Maven coordinates are:

```text
org.example:publishing:1.0.0
```

## Publication

The `maven-publish` plugin is configured to publish the Java component:

```groovy
publishing {
    publications {
        create('mavenJava', MavenPublication) {
            from components.java
        }
    }
}
```

The `from components.java` declaration tells Gradle to publish the component produced by the Java plugin.

## Build the Library

Build the project with:

```bash
./gradlew build
```

The generated JAR can be found under:

```text
build/libs/
```

## Publish to Maven Local

Publish the library to the local Maven repository:

```bash
./gradlew publishToMavenLocal
```

The artifact is installed in the user's local Maven repository.

Its location follows the standard Maven repository layout:

```text
~/.m2/repository/org/example/publishing/1.0.0/
```

The directory contains the published POM and JAR.

## Understanding the Task Graph

Publishing an artifact is not performed by a single task. When you run:

```bash
./gradlew publishToMavenLocal
```

Gradle builds a **task graph** containing the tasks required to perform the publication.

For example, the graph may include tasks related to:

* compiling the Java source code;
* creating the JAR;
* generating the Maven POM;
* publishing the artifact to Maven Local.

Gradle then determines which of these tasks actually need to execute.

This is why the build summary may report something such as:

```text
5 actionable tasks: 3 executed, 2 up-to-date
```

This means that:

* **5 tasks** were considered relevant to the requested operation;
* **3 tasks** were executed;
* **2 tasks** were already up-to-date and therefore did not need to execute again.

On a subsequent execution, if nothing has changed, more tasks may be reported as `UP-TO-DATE`.

This illustrates an important Gradle concept: **the task graph determines which tasks are required, while incremental build checks determine whether each task needs to execute.**

You can inspect the task graph without executing the tasks:

```bash
./gradlew publishToMavenLocal --dry-run
```

## Inspect Publishing Tasks

List the tasks provided by the publishing plugin:

```bash
./gradlew tasks --group publishing
```

You can also obtain more detailed information about task execution with:

```bash
./gradlew publishToMavenLocal --info
```

## Key Concepts

This example illustrates:

* Java library projects
* Maven coordinates (`group`, artifact, `version`)
* The `maven-publish` plugin
* Publications and components
* Publishing to Maven Local
* Gradle publishing tasks
* Task graphs
* Incremental builds and `UP-TO-DATE` tasks

The example intentionally publishes only to the local Maven repository. A remote Maven repository can be configured by adding a repository to the `publishing.repositories` section.
