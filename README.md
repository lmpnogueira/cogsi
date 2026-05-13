# Configuração e Gestão de Sistemas (COGSI) Course: Semester Overview

Welcome to the official repository for the **COGSI** course. This semester, we explore the tools and methodologies that bridge the gap between software development and systems operations.

---

## Course Modules

The curriculum is structured to follow the natural lifecycle of a modern application:

### 1. Source Control Management (Git)
The foundation of collaboration. We cover branching strategies (GitFlow), merging, and the importance of a clean commit history as the single source of truth for automation.

### 2. Build Automation (Gradle)
Moving beyond manual compilation. We explore dependency management, build lifecycles, and how to package applications into reproducible artifacts.

### 3. Virtualization (Vagrant)
"It works on my machine" ends here. We use Vagrant to create identical, disposable development environments using VirtualBox, ensuring parity between all students' machines.

### 4. Configuration Management (Ansible)
Automating server configuration. We use Ansible Playbooks to manage system state, install software, and configure services without manual intervention.

### 5. Containerization (Docker)
The shift to microservices. We learn to package applications with their entire runtime environment, exploring Dockerfiles, image optimization, and container networking.

### 6. Continuous Integration and Delivery (Jenkins)
The heart of automation. We build CI/CD pipelines that trigger on every code push, running builds, tests, and deployments automatically.

### 7. Observability (Prometheus, Alert Manager, and Grafana)
The feedback loop. We implement continuous monitoring with Prometheus to visualize system health and analyze performance, configure proactive alerting with Alertmanager, and use Grafana to create dynamic dashboards that provide a real-time overview of the infrastructure's operational state.

---

## Getting Started

Each folder in this repository corresponds to a specific module. Inside each folder, you will find:
- **Configuration Samples:** (e.g., `Vagrantfile`, `docker-compose.yml`, `prometheus.yml`).
- **Assets:** Sample code and scripts.

---
**Professor:** Luís Nogueira/ISEP (lmn@isep.ipp.pt)  
**Academic Year:** 2026/2027
