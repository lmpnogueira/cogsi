# Module: Virtualization 

In modern DevOps, source code alone is not enough. Applications require reliable, repeatable, and reproducible infrastructure to run consistently across development, testing, and deployment environments. Infrastructure must therefore be defined, provisioned, and managed through automation to transform machine configuration into versioned, portable, and operational systems.

This directory contains the configuration files, examples, and practical resources required for the Virtualization module. The content is divided into two distinct stages to help you understand the evolution from single-machine provisioning to multi-machine infrastructure orchestration.

---

## Repository Structure

### 1. `/single_vm`

**Used in week 1.** This folder focuses exclusively on single-machine virtualization with Vagrant.

- **Goal:** Understand virtualization fundamentals, Vagrant providers, box compatibility, VM lifecycle management, provisioning, synced folders, and basic networking.
- **Key Files:**
    - `Vagrantfile`: Main declarative configuration for a single VM.
    - `bootstrap.sh`: Basic provisioning script for automated setup.
    - Troubleshooting and debugging support materials.

### 2. `/multi_vms`

**Used in week 2.** This folder explores multi-machine Vagrant environments and secure infrastructure design.

- **Goal:** Build a complete end-to-end infrastructure where multiple VMs interact through private networks, provisioning dependencies, SSH authentication, and operational security.
- **Key Files:**
    - `Vagrantfile`: Main declarative configuration for multiple VMs.
    - Modular provisioning scripts.
---

## Prerequisites
- **Vagrant**: Installed and available in your system path.
- **Virtualization provider**: VirtualBox or VMware.
- **Compatible base box**: Ensure provider and CPU architecture compatibility (`amd64` / `arm64`).
- **SSH**: Required for secure VM access and advanced provisioning scenarios.

