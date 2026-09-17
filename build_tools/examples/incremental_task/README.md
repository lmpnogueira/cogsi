# Incremental Task Example

This example demonstrates how Gradle uses **task inputs and outputs** to support incremental builds and avoid unnecessary work.

The project defines two custom tasks:

* `generateReport` — reads an input file and generates a report.
* `packageReport` — packages the generated report and depends on `generateReport`.

## Project Structure

```text
incremental_task/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── gradle/
│   └── wrapper/
└── input/
    └── messages.txt
```

## Tasks

### `generateReport`

The task:

1. Reads `input/messages.txt`.
2. Counts the total and non-empty lines.
3. Generates `build/reports/messages.txt`.

The input and output are explicitly declared:

```groovy
inputs.file(inputFile)
outputs.file(outputFile)
```

This allows Gradle to determine whether the task needs to run again.

Run it with:

```bash
./gradlew generateReport
```

On the first execution, the task runs normally:

```text
> Task :generateReport
Report generated: .../build/reports/messages.txt

BUILD SUCCESSFUL
```

Run it again without changing the input:

```bash
./gradlew generateReport
```

Gradle can skip the task:

```text
> Task :generateReport UP-TO-DATE

BUILD SUCCESSFUL
```

Now modify `input/messages.txt` and run the task again. Since an input has changed, Gradle executes the task again.

### `packageReport`

The second task packages the generated report.

It declares a dependency on `generateReport`:

```groovy
dependsOn(tasks.named('generateReport'))
```

Therefore:

```text
generateReport
       │
       ▼
packageReport
```

Run both tasks through:

```bash
./gradlew packageReport
```

Gradle first ensures that `generateReport` has produced the required report and then executes `packageReport`.

Because both tasks declare their inputs and outputs, Gradle can determine independently whether each task needs to execute.

## Exploring Incremental Builds

Try the following sequence:

```bash
./gradlew clean
./gradlew packageReport
./gradlew packageReport
```

The first execution performs the work. On the second execution, the tasks should be reported as `UP-TO-DATE`.

Now modify `input/messages.txt`:

```bash
echo "A new message was added." >> input/messages.txt
```

and run:

```bash
./gradlew packageReport
```

The change to the input invalidates the output of `generateReport`, causing that task to execute again. Gradle then evaluates `packageReport` based on its inputs and outputs.

You can also inspect the task graph without executing the tasks:

```bash
./gradlew packageReport --dry-run
```

## Key Concepts

This example illustrates several important Gradle concepts:

* **Task inputs** — files or values a task reads.
* **Task outputs** — files or values a task produces.
* **Incremental builds** — avoiding work when relevant inputs have not changed.
* **`UP-TO-DATE`** — Gradle skips a task when its inputs and outputs indicate that no work is necessary.
* **Task dependencies** — `dependsOn` establishes that one task must execute before another.
* **Task graph** — Gradle determines which tasks are required and their execution order.

The example intentionally uses simple custom tasks so that the relationship between **inputs, outputs, task dependencies, and incremental execution** is easy to observe.
