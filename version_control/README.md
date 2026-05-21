# Module: Version Control

Version control is a fundamental DevOps practice that enables teams to track changes, collaborate safely, maintain project history, and manage software evolution over time.

This directory contains the examples and supporting files used throughout the Version Control module. The examples introduce core Git concepts, including repository creation, commits, branching, merging, conflict resolution, and repository configuration through `.gitignore` and `.gitattributes` files.

---

## Repository Structure

### 1. `foundations/`

**Used throughout the course** This folder provides a discussion of common Git workflows and repository management practices.

- **Goal:** Learn how Git records project history, supports collaborative development through branching and merging, and uses repository configuration files to manage tracked content consistently across different environments.
- **Key Files:**
    - `.gitignore`: Specifies files and directories that Git should not track, such as generated artefacts, temporary files, IDE settings, logs, and other machine-specific content.
    - `.gitatributes`: Defines repository-wide rules for handling files, including line-ending normalisation, diff behaviour, merge strategies, and binary file classification.
    - `foundations.md`: Version control foundations for efficient software delivery.

---

## Important Repository Configuration Files

### `.gitignore`

The `.gitignore` file prevents unnecessary files from being added to the repository. Typical examples include:

- Build artefacts (`build/`, `target/`, `dist/`).
- Temporary files (`*.tmp`, `*.log`).
- IDE configuration directories (`.idea/`, `.vscode/`).
- Operating-system-specific files (`.DS_Store`, `Thumbs.db`).

Ignoring such files keeps the repository clean, reduces merge conflicts, and avoids committing machine-specific or generated content.

### `.gitattributes`

The `.gitattributes` file defines how Git should treat different file types across all contributors. Common uses include:

- Normalising line endings between Windows, macOS, and Linux systems.
- Marking binary files to prevent incorrect text-based diffs.
- Defining custom merge or diff behaviour for specific file types.
- Improving repository consistency in multi-platform development environments.

A typical configuration ensures consistent handling of text files while correctly identifying binary assets.

---

## Prerequisites
- **Git**: Git version 2.x or later.
- **Text Editor or IDE**: Any editor capable of modifying plain text files.
- **Command-line terminal**: Basic familiarity with terminal commands is recommended, as all examples use the Git command-line interface.
