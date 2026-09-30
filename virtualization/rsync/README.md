# Rsync Synced Folder

This example demonstrates how to use **Rsync** as a Vagrant synced-folder mechanism.

Unlike a traditional shared folder, Rsync synchronizes files from the host to the guest by transferring changed files. The guest therefore receives a copy of the files rather than accessing the host directory directly.

This approach can provide better I/O performance than some shared-folder implementations, particularly when working with large projects or many files.

## Project Structure

```text
rsync/
├── Vagrantfile
├── README.md
└── web/
    └── index.html
````

## How It Works

The following configuration synchronizes the `web` directory from the host to `/var/www/html` inside the VM:

```ruby
config.vm.synced_folder "./web", "/var/www/html",
  type: "rsync"
```

The relationship between the host and the guest is therefore:

```text
Host                              VM

web/
└── index.html  ─── Rsync ───>  /var/www/html/
                                 └── index.html
```

Rsync is a synchronization mechanism rather than a shared filesystem. The files in the guest are copies of the files from the host.

## Requirements

Rsync must be installed and available on the host system.

On Linux and macOS, Rsync is normally already available. Verify it with:

```bash
rsync --version
```

On Windows, an Rsync-compatible environment is required.

## Starting the VM

Start the environment with:

```bash
vagrant up
```

The VM is configured with:

* Ubuntu 24.04
* Nginx
* A forwarded port from guest port `80` to host port `8080`
* An Rsync synced folder

The web server is available from the host at:

```text
http://localhost:8080
```

## Synchronizing Files

The initial `vagrant up` performs the synchronization.

After modifying files in the `web` directory, synchronize the changes explicitly with:

```bash
vagrant rsync
```

For example, edit:

```text
web/index.html
```

Change:

```html
<h1>COGSI Rsync Demo</h1>
```

to:

```html
<h1>COGSI Rsync Demo - Updated</h1>
```

Then run:

```bash
vagrant rsync
```

Refresh:

```text
http://localhost:8080
```

The updated page should now be served by Nginx inside the VM.

## Inspecting the Synchronized Files

Connect to the VM:

```bash
vagrant ssh
```

Then inspect the synchronized file:

```bash
cat /var/www/html/index.html
```

The contents should correspond to the current version of:

```text
web/index.html
```

on the host.

Exit the VM with:

```bash
exit
```

## Checking the Synchronization

The synchronization can also be observed by modifying the file directly on the host and then running:

```bash
vagrant rsync
```

Only changed files need to be transferred.

This is one of the main differences between Rsync-based synchronization and a traditional shared-folder mechanism.

## Rsync vs. Shared Folders

A traditional shared folder provides direct access to files on the host from inside the VM.

With Rsync:

```text
Traditional shared folder:

Host filesystem
       │
       │ direct access
       ▼
      VM


Rsync:

Host filesystem
       │
       │ synchronization
       ▼
Guest filesystem
```

Rsync therefore provides a copy of the files inside the guest rather than a live shared filesystem.

## Optional: Automatic Synchronization

Vagrant can also monitor the synchronized directory and automatically synchronize changes.

This can be enabled by adding the following option to the synced-folder configuration:

```ruby
config.vm.synced_folder "./web", "/var/www/html",
  type: "rsync",
  rsync__auto: true
```

With this configuration, Vagrant automatically synchronizes changes detected in the `web` directory.

For this example, automatic synchronization is intentionally not enabled so that the synchronization step can be observed explicitly using:

```bash
vagrant rsync
```

## Useful Commands

Start the VM:

```bash
vagrant up
```

Synchronize files:

```bash
vagrant rsync
```

Connect to the VM:

```bash
vagrant ssh
```

Check the VM status:

```bash
vagrant status
```

Stop the VM:

```bash
vagrant halt
```

Destroy the VM:

```bash
vagrant destroy
```

## Key Concepts

This example demonstrates:

* Rsync as a Vagrant synced-folder mechanism
* Host-to-guest file synchronization
* Synchronization of changed files
* The `vagrant rsync` command
* The difference between synchronization and a traditional shared folder
* Optional automatic synchronization with `rsync__auto`

The main idea is:

> **Rsync synchronizes files from the host to the guest; it does not provide a shared filesystem.**

