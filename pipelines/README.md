

# Module: CI/CD Pipelines

Modern CI/CD systems require more than isolated build commands or manually executed automation steps. As software systems evolve, delivery workflows must become reproducible, version-controlled, adaptive, and scalable. Jenkins Pipelines transform automation from ad-hoc job configuration into declarative, code-driven orchestration capable of managing builds, tests, validations, deployments, and operational workflows consistently across development lifecycles.

This directory contains the configuration files, examples, and practical resources required for the Jenkins Pipelines module. The content is divided into two stages that progressively introduce pipeline fundamentals and more advanced orchestration concepts used in modern CI/CD environments.

Some concepts, such as credentials management, distributed builds, automated triggers, and Multibranch Pipeline configuration, are discussed in the lectures and may require additional Jenkins configuration or plugins. The provided Jenkinsfiles focus on pipeline syntax and the core execution concepts demonstrated by each example.

---

## Repository Structure

### 1. `simple/`

**Used in week 1.** This folder introduces foundational Jenkins Pipeline concepts using Declarative Pipelines.

- **Goal:** Understand Pipeline as Code, Jenkinsfile structure, stages, steps, environment variables, parameters, options, artifact archiving, and basic CI workflows.
- **Key Files:**
  - `Jenkinsfile`: Main declarative pipeline definition.

Credentials management is discussed in the lectures but is not explicitly demonstrated in this Jenkinsfile.

### 2. `advanced/`

**Used in week 2.** This folder explores more advanced orchestration concepts within Jenkins Pipelines.

- **Goal:** Extend basic pipelines with conditional execution, branch-aware workflows, parallel execution, and artifact management.
- **Key Files:**
  - `Jenkinsfile`: Advanced pipeline definition demonstrating orchestration concepts.

The advanced example demonstrates branch-aware execution, conditional stages, and parallel validation. It assumes execution within a Multibranch Pipeline configured in Jenkins. Build triggers, such as GitHub webhooks, must be configured separately in Jenkins and GitHub.

Distributed builds and automated triggers are discussed in the lectures but are not implemented directly in the provided Jenkinsfile. Deployment stages are illustrative placeholders and do not perform actual deployments.

---

## Prerequisites

- **Jenkins:** Installed locally, in a VM, or accessible through a shared server.
- **Git:** Required for repository checkout and Multibranch Pipeline integration.
- **Java Development Kit (JDK):** Required to execute Gradle-based builds.
- **Gradle Wrapper:** Included in the repository to ensure reproducible builds without requiring a local Gradle installation.

### Recommended Jenkins Plugins

The following plugins are required or recommended for the provided examples:

| Plugin | Purpose |
|---|---|
| Pipeline | Declarative Pipeline execution |
| Git | Git repository integration and checkout |
| JUnit | Publishing test results |
| Timestamper | Adding timestamps to console output |
| Workspace Cleanup | Cleaning the workspace after pipeline execution |

Additional plugins may be required depending on the Jenkins configuration and the concepts explored during the lectures.

