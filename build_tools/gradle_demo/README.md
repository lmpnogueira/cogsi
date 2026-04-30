# Gradle Demo

This project is a **basic multithreaded chat room application** used for
teaching modern Gradle concepts.

The system consists of: 
- A **chat server** 
- A **chat client** 
- A simple text-based chat protocol

The server supports multiple concurrent clients using threads. Each
client must register a unique screen name before participating in the
chat. Messages are then broadcast to all connected clients.

## Project Goals

This project is intentionally designed as a **teaching and
experimentation environment**. You will progressively:

-   Understand Gradle build lifecycle
-   Add and manage dependencies
-   Create and extend custom tasks
-   Learn how Java applications are executed via classpath
-   Compare `java -cp` vs `gradle run`
-   Prepare for unit testing and CI pipelines

## Prerequisites

To build and run this project, you need:

-   Java JDK 21 (toolchain-managed by Gradle)
-   Gradle 9 (if the provided Gradle Wrapper is not used)
-   Internet access for dependency resolution (Maven Central)

Note: Gradle will automatically download and use JDK 21 via toolchains
if properly configured.

## Build

To build the project:

``` bash
./gradlew clean build
```

This will: 
- Compile the source code 
- Run tests (if present) 
- Generate the application JAR 
- Resolve dependencies (Log4J, etc.)

## Exploring Available Gradle Tasks

List all tasks:

``` bash
./gradlew tasks
```

## Run

To run the main application:

``` bash
./gradlew run
```

The `main` task executed via `./gradlew run` is not used to start the chat application itself. Instead, it is designed to provide guidance to the user by printing instructions on how to manually run both the server and the client.

This separation is intentional, as the project consists of two independent applications: the chat server and the chat client. Each must be executed separately, typically in different terminals, to simulate a real distributed system.

## Running the Server (Manual execution)

Gradle’s default `jar` task produces a thin JAR, meaning it only contains the compiled application classes and resources, but not the external dependencies (such as Log4J). 

As a result, before running the application manually using `java -cp`, you must first execute the `packageApp` task to collect the required dependencies.

``` bash
./gradlew packageApp
```

A fat (or uber) JAR would bundle both the application code and all its dependencies into a single executable file, eliminating the need for a separate `packageApp` step. Implementing a fat JAR is left as an exercise.

To run the server in the current machine:

``` bash
java -cp "app/build/libs/chat-server-1.0.jar:app/build/libs/lib/*" org.example.ChatServerApp <port>
```

Example:

``` bash
java -cp "app/build/libs/chat-server-1.0.jar:app/build/libs/lib/*" org.example.ChatServerApp 59001
```

On Windows, replace `:` with `;` in the classpath separator and adjust the path format accordingly.

## Running the Client (Using the Gradle task)

``` bash
./gradlew runClient
```

This task assumes that the chat server is running on `localhost` and listening on port `59001`. If you wish to use different values, you can override them using Gradle properties (recommended) or modify the default values defined in the `runClient` task in the `build.gradle` file.

To override parameters use `-P<parameter=value>`:

``` bash
./gradlew runClient -PserverIP=192.168.56.3 -PserverPort=9000
```

To run multiple clients, simply open additional terminal windows and execute the `runClient` Gradle task in each one. Each client instance will operate independently and connect to the same server.
