# Module: Configuration Management

In modern DevOps, provisioning infrastructure alone is not enough. Systems must be configured, secured, validated, and maintained consistently through repeatable, idempotent, and scalable automation.

This directory contains the configuration files, examples, and assignment resources required for the Configuration Management module. The content is divided into distinct stages to help you understand the evolution from basic Ansible playbooks to modular roles, security hardening, and multi-VM configuration management.

---

## Repository Structure

### 1. `monolithic/`

**Used in week 1.** This folder focuses on foundational configuration management with Ansible integrated into Vagrant-managed infrastructure.

- **Goal:** Understand Ansible fundamentals, YAML syntax, inventory structure, playbooks, tasks, variables, handlers, templates, idempotence, and basic Vagrant + Ansible provisioning.
- **Key Files:**
    - `Vagrantfile`: Main declarative configuration for provisioning a VM with Ansible.
    - `site.yml`: Core Ansible playbook demonstrating configuration management basics.

### 2. `roles/`

**Used in week 1.** This folder explores modular Ansible design through reusable roles and scalable automation structure.

- **Goal:** Build structured, reusable automation using roles, defaults, handlers, templates, variable overrides, and standardised role-based project organisation.
- **Key Files:**
    - `site.yml`: Main playbook invoking roles.
    - `roles/`: Standard Ansible role directory structure.
    - Role-specific tasks, handlers, defaults, and templates.
---

## Prerequisites
- **Vagrant**: Installed and available in your system path.
- **Virtualization provider**: VirtualBox or VMware.
- **Compatible base box**: Ensure provider and CPU architecture compatibility (`amd64` / `arm64`).
- **Ansible**: Installed on the control node.
- **SSH**: Required for Ansible communication with managed nodes.

