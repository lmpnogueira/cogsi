# Module: Virtualization

In modern DevOps, source code alone is not enough. Applications require reliable, repeatable, and reproducible infrastructure to run consistently across development, testing, and deployment environments. Infrastructure must therefore be defined, provisioned, and managed through automation to transform machine configuration into versioned, portable, and operational systems.

This directory contains the configuration files, examples, and practical resources required for the Virtualization module. The content is divided into two main stages, complemented by focused examples, to help you understand the evolution from single-machine provisioning to multi-machine infrastructure orchestration and supporting DevOps practices.

---

## Repository Structure

### 1. `single_vm/`

**Used in week 1.** This folder focuses exclusively on single-machine virtualization with Vagrant.

* **Goal:** Understand virtualization fundamentals, Vagrant providers, box compatibility, VM lifecycle management, provisioning, synced folders, environment configuration, and basic networking.

* **Key Files:**

  * `Vagrantfile`: Main declarative configuration for a single VM.
  * `provisioning/bootstrap.sh`: Basic provisioning script for automated setup.
  * `shared/`: Example of a synchronized folder.
  * Troubleshooting and debugging support materials.

### 2. `multi_vms/`

**Used in week 2.** This folder explores multi-machine Vagrant environments and infrastructure orchestration.

* **Goal:** Build a multi-VM environment where machines interact through private networks, machine-specific provisioning, shared configuration, forwarded ports, and hostname mapping.

* **Key Files:**

  * `Vagrantfile`: Main declarative configuration for multiple VMs.
  * `provisioning/base.sh`: Common provisioning applied to all machines.
  * `provisioning/web.sh`: Web machine provisioning.
  * `provisioning/db.sh`: Database machine provisioning.
  * `shared/` and `shared_webapp/`: Examples of synchronized folders.

### 3. `rsync/`

This folder provides a focused example of **Rsync-based synchronized folders**.

* **Goal:** Understand how Vagrant can synchronize files from the host to the guest using Rsync instead of a traditional shared-folder mechanism.

* **Key Files:**

  * `Vagrantfile`: Configures the Rsync synchronized folder and a simple web server.
  * `web/index.html`: Example content synchronized to the VM.
  * `README.md`: Instructions for running and testing the example.

* **Key concepts:**

  * Rsync synchronized folders.
  * Host-to-guest file synchronization.
  * `vagrant rsync`.
  * Difference between synchronization and a shared filesystem.
  * Optional automatic synchronization with `rsync__auto`.

### 4. `ssh_agent/`

This folder provides a focused example of **SSH agent forwarding**.

* **Goal:** Understand how a VM can use SSH keys loaded in the host's SSH agent without copying the corresponding private keys into the VM.

* **Key Files:**

  * `Vagrantfile`: Enables SSH agent forwarding.
  * `provisioning/setup.sh`: Installs the SSH client and provides the environment for the demonstration.
  * `README.md`: Instructions for testing SSH agent forwarding.

* **Key concepts:**

  * SSH agent forwarding.
  * `config.ssh.forward_agent`.
  * `SSH_AUTH_SOCK`.
  * Using keys loaded in the host's SSH agent.
  * Avoiding the transfer of private keys to the VM.
  * Security considerations associated with agent forwarding.

---

## Prerequisites

* **Vagrant**: Installed and available in your system path.

* **Virtualization provider**: VirtualBox or VMware Desktop.

* **Compatible base box**: Ensure provider and CPU architecture compatibility (`amd64` / `arm64`).

* **SSH**: Required for secure VM access and for the SSH agent forwarding example.

* **Rsync**: Required on the host system for the `rsync/` example.

### VMware Desktop

When using VMware Fusion or VMware Workstation, provider-specific configuration may be required. In particular, private-network addressing can depend on the VMware `vmnet` configuration of the host.

The `multi_vms/` example uses explicit private IP addresses, so the private-network configuration may need to be adjusted for the local VMware environment.

### SSH Agent Forwarding

The `ssh_agent/` example requires an SSH agent running on the host with at least one key loaded.

Check the currently loaded keys with:

```bash
ssh-add -l
```

If necessary, add a key with:

```bash
ssh-add ~/.ssh/id_ed25519
```

The private key remains on the host; only access to the SSH agent is forwarded to the VM.
