# Module: Build Tools

In modern DevOps, source code alone is not enough. Software must be transformed into reliable, repeatable, testable artefacts through disciplined build automation.

This directory contains the projects, build configurations, and supporting files required for the Build Tools module. The content is divided into two distinct stages to help you understand the progression from basic build automation concepts with a simple Gradle project to migrating the `bookstore` project to Gradle in week 2.

---

## Repository Structure

### 1. `gradle_demo/`

**Used in week 1.** This project focuses exclusively on Gradle fundamentals using a simple Java application.

- **Goal:** Understand the purpose of build tools, how Gradle structures and automates the build lifecycle, how to use the Gradle Wrapper, and how to execute core tasks such as compiling, testing, packaging, and running an application.

- **Key Files:**
  - `build.gradle`: Main build configuration file using Groovy DSL, defining plugins, dependencies, and tasks.
  - `settings.gradle`: Defines the project name and build identity.
  - `gradlew / gradlew.bat`: Gradle Wrapper scripts for reproducible, zero-install builds.
  - `src/main/java/`: Application source code.
  - `src/test/java/`: Unit tests (introduced in CA1 Part 1).

---

### 2. `examples/`

Additional independent examples illustrating specific Gradle concepts:

- `application_distribution/`: Demonstrates application packaging and distribution using the Gradle Application Plugin, including `installDist`, `distZip`, and `distTar`.
- `incremental_task/`: Demonstrates incremental builds using task inputs and outputs and Gradle's `UP-TO-DATE` mechanism.
- `publishing/`: Demonstrates publishing a Java library to a Maven repository using the `maven-publish` plugin.
- `task_types/`: Demonstrates how to define reusable custom Gradle task types by extending built-in task types and encapsulating common task configuration.

These examples are provided as reference material and are independent of the assessed `bookstore` migration.

---

## Prerequisites

- **Java Development Kit (JDK)**: Ensure a compatible JDK is installed (JDK 17 or later, according to project requirements).

- **Gradle**: A global Gradle installation is optional but useful for generating the initial Wrapper; afterwards, prefer the included Gradle Wrapper (`gradlew` / `gradlew.bat`).