# Module: Containerization

In modern DevOps, provisioning complete virtual machines is often too heavy for many application delivery workflows. Applications increasingly require lightweight, portable, and reproducible runtime environments that package code, dependencies, configuration, and operational behavior into consistent deployment units. Containerization transforms application delivery from machine-centric provisioning into image-based, declarative, and scalable system design.

This directory contains the configuration files, examples, and practical resources required for the Containerization module. The content is divided into two distinct stages to help you understand the evolution from single-container application packaging to multi-container service orchestration.

---

## Repository Structure

### 1. `single_container/`

**Used in week 1.** This folder focuses exclusively on building and running a single image and container with Docker.

- **Goal:** Understand container fundamentals, images vs containers, Dockerfile structure, build context, `.dockerignore`, container lifecycle management, port publishing, environment variables, volumes, and registry-ready image design.
- **Key Files:**
    - `Dockerfile`: Main declarative configuration for building the application image.
    - `.dockerignore`: Build context filtering and image optimization.
    - `app.py`: Example containerized application.
    - `requirements.txt`: Application dependencies.

### 2. `multi_vms/`

**Used in week 2.** This folder explores multi-container environments using Docker Compose.

- **Goal:** Build a complete multi-service application stack where containers interact through service discovery, reverse proxies, environment variables, health checks, networks, and persistent storage.
- **Key Files:**
    - `compose.yaml`: Main declarative configuration for orchestrating multiple services.
    - `.env`: Centralized runtime configuration. 
    - `web/`: Custom application image source.
    - `nginx/default.conf`: Reverse proxy configuration.
    - Named volumes, bind mounts, and network isolation examples.
---

## Prerequisites
- **Docker Engine / Docker Desktop**: Installed and available in your system path.
- **Docker Compose plugin**: Available via `docker compose`.
- **Command-line terminal**: Required for build, execution, debugging, and lifecycle management.

