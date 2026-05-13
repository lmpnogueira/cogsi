# syntax=docker/dockerfile:1

# =============================================================================
# Dockerfile — COGSI / Containerization Part I
# -----------------------------------------------------------------------------
# Purpose:
# - Understand image construction
# - Understand container runtime configuration
# - Understand persistence with volumes
# - Understand environment variable overrides
#
# Directory structure:
#   .
#   ├── Dockerfile
#   ├── requirements.txt
#   ├── app.py
#   ├── .dockerignore
#   └── data/  (optional, for local development)
# -----------------------------------------------------------------------------


# -----------------------------------------------------------------------------
# Base image
#
# We choose an official Python image that already includes Python,
# reducing size and simplifying provisioning.
# -----------------------------------------------------------------------------
FROM python:3.12-slim


# -----------------------------------------------------------------------------
# Working directory inside the image
#
# All following COPY/RUN/CMD instructions execute relative to /app.
# -----------------------------------------------------------------------------
WORKDIR /app


# -----------------------------------------------------------------------------
# Copy dependency list first
#
# This improves Docker layer caching:
# if requirements.txt does not change, Docker can reuse the pip install layer.
# -----------------------------------------------------------------------------
COPY requirements.txt .


# -----------------------------------------------------------------------------
# Install application dependencies
#
# --no-cache-dir reduces final image size.
# -----------------------------------------------------------------------------
RUN pip install --no-cache-dir -r requirements.txt


# -----------------------------------------------------------------------------
# Copy application source code
#
# -----------------------------------------------------------------------------
COPY app.py .


# -----------------------------------------------------------------------------
# Default environment variable
#
# Can be overridden at runtime:
# docker run -e APP_VERSION=2.0 ...
# -----------------------------------------------------------------------------
ENV APP_VERSION=1.0


# -----------------------------------------------------------------------------
# Document the application port
#
# This does NOT publish the port automatically.
# It serves as metadata and documentation.
# -----------------------------------------------------------------------------
EXPOSE 8000


# -----------------------------------------------------------------------------
# Persistent storage
#
# Docker creates a volume for /data unless a bind mount or named volume is used.
#
# Example:
# docker run -v single-app-data:/data ...
# -----------------------------------------------------------------------------
VOLUME ["/data"]


# -----------------------------------------------------------------------------
# Default startup command
#
# When the container starts, this launches the Flask app.
# -----------------------------------------------------------------------------
CMD ["python", "app.py"]


# =============================================================================
# BUILD INSTRUCTIONS
# =============================================================================
#
# Build the image:
#
#   docker build -t single-app:1.0 .
#
# Inspect local images:
#
#   docker image ls
#
# View image layers:
#
#   docker history single-app:1.0
# =============================================================================



# =============================================================================
# RUN INSTRUCTIONS
# =============================================================================
#
# Run the container:
#
#   docker run -d --name single-app-container -p 8000:8000 -v single-app-data:/data single-app:1.0
#
# Explanation:
# -d                     Run in detached mode
# --name                 Assign explicit container name
# -p 8000:8000           Publish host port 8000 to container port 8000
# -v single-app-data     Persist logs outside container lifecycle
# =============================================================================



# =============================================================================
# TESTING
# =============================================================================
#
# Access the application:
#
#   curl http://localhost:8000
#
# Expected output:
#
# {
#   "message":"Hello from Docker!",
#   "hostname":"<container-id>",
#   "version":"1.0"
# }
#
# Override runtime version:
#
#   docker run -d --name single-app-v2 -p 8001:8000 -e APP_VERSION=2.0 single-app:1.0
#
# Test:
#
#   curl http://localhost:8001
# =============================================================================



# =============================================================================
# INSPECTION / DEBUGGING
# =============================================================================
#
# List containers:
#
#   docker ps -a
#
# View logs:
#
#   docker logs single-app-container
#
# Interactive shell:
#
#   docker exec -it single-app-container /bin/sh
#
# Inspect persisted log file:
#
#   docker exec single-app-container cat /data/access.log
# =============================================================================



# =============================================================================
# CLEANUP
# =============================================================================
#
# Stop:
#
#   docker container stop single-app-container
#
# Remove:
#
#   docker container rm single-app-container
#
# Remove volume if desired:
#
#   docker volume rm single-app-data
# =============================================================================