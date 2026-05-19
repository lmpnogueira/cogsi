# Module: CI/CD Pipelines

Modern CI/CD systems require more than isolated build commands or manually executed automation steps. As software systems evolve, delivery workflows must become reproducible, version-controlled, adaptive, and scalable. Jenkins Pipelines transform automation from ad-hoc job configuration into declarative, code-driven orchestration capable of managing builds, tests, validations, deployments, and operational workflows consistently across development lifecycles.

This directory contains the configuration files, examples, and practical resources required for the Jenkins Pipelines module. The content is divided into two stages that progressively introduce pipeline fundamentals and more advanced orchestration concepts used in modern CI/CD environments.

---

## Repository Structure

### 1. `simple/`

**Used in week 1.** This folder introduces foundational Jenkins Pipeline concepts using Declarative Pipelines.

- **Goal:** Understand Pipeline as Code, Jenkinsfile structure, stages, steps, environment variables, parameters, options, artifact archiving, credentials management, and basic CI workflows.
- **Key Files:**
    - `Jenkinsfile`: Main declarative pipeline definition.

### 2. `advanced/`

**Used in week 2.** This folder explores more advanced orchestration and scalability concepts within Jenkins Pipelines.

- **Goal:** Extend static pipelines into dynamic orchestration systems using conditional execution, parallelism, distributed builds, automated triggers, and Multibranch Pipelines.
- **Key Files:**
    - `Jenkinsfile`: Advanced pipeline definition demonstrating orchestration concepts.

---

## Prerequisites
- **Jenkins**: Installed locally, in a VM, or accessible through a shared server.
- **Git**: Required for repository checkout and Multibranch Pipeline integration.
- **Java Development Kit (JDK)**: Required to execute Gradle-based builds.
- **Gradle Wrapper**: Included in the repository to ensure reproducible builds without requiring a local Gradle installation.

