# SSH Agent Forwarding

This example demonstrates how to use **SSH agent forwarding** with Vagrant.

SSH agent forwarding allows a VM to use SSH keys loaded in the SSH agent running on the host without copying the corresponding private keys into the VM.

This is useful when a VM needs to authenticate to another SSH server on behalf of the user.

## Project Structure

```text
ssh_agent/
├── Vagrantfile
├── README.md
└── provisioning/
    └── setup.sh
````

## How It Works

The Vagrantfile enables SSH agent forwarding with:

```ruby
config.ssh.forward_agent = true
```

The SSH private key remains on the host:

```text
Host
├── private SSH key
└── ssh-agent
       │
       │ agent forwarding
       ▼
    Vagrant VM
       │
       │ SSH authentication
       ▼
  Remote SSH server
```

The VM does not receive a copy of the private key. Instead, SSH forwards access to the SSH agent running on the host.

## Requirements

An SSH agent must be running on the host and must have at least one SSH key loaded.

Check the keys currently loaded in the SSH agent with:

```bash
ssh-add -l
```

If no keys are loaded, add an appropriate key. For example:

```bash
ssh-add ~/.ssh/id_ed25519
```

Then verify again:

```bash
ssh-add -l
```

On macOS, the SSH agent is normally available by default.

## Starting the VM

Start the environment with:

```bash
vagrant up
```

The VM can then be accessed with:

```bash
vagrant ssh
```

## Checking Agent Forwarding

Inside the VM, check the `SSH_AUTH_SOCK` environment variable:

```bash
echo $SSH_AUTH_SOCK
```

If agent forwarding is working, this should display the path to the forwarded SSH agent socket.

You can then list the public keys available through the forwarded agent:

```bash
ssh-add -L
```

The output should contain the public keys currently available from the SSH agent on the host.

The private keys themselves are not copied to the VM.

## Testing SSH Authentication

Agent forwarding becomes useful when the VM needs to connect to another SSH server.

For example:

```bash
ssh user@remote-host
```

If the corresponding private key is loaded in the host's SSH agent and the remote server accepts that key, the authentication can be performed using the forwarded agent.

The private key remains on the host throughout the process.

## Comparing Direct Key Access and Agent Forwarding

Without agent forwarding, a private key would have to be available on the machine initiating the SSH connection:

```text
Host
└── private key

VM
└── no private key
       │
       └── SSH authentication not possible using that key
```

With agent forwarding:

```text
Host
├── private key
└── ssh-agent
       │
       │ forwarded agent
       ▼
      VM
       │
       └── SSH authentication
```

The VM can request authentication operations from the forwarded agent without accessing the private key itself.

## Important Security Consideration

SSH agent forwarding should only be used with trusted machines.

The private key is not transferred to the remote machine, but processes running on that machine may be able to use the forwarded agent to perform authentication operations while the SSH session is active.

Therefore, agent forwarding should be used carefully, particularly when connecting to machines that are not fully trusted.

## Useful Commands

Check keys loaded in the host's SSH agent:

```bash
ssh-add -l
```

Add a key to the host's SSH agent:

```bash
ssh-add ~/.ssh/id_ed25519
```

Start the VM:

```bash
vagrant up
```

Connect to the VM:

```bash
vagrant ssh
```

Check the forwarded agent socket:

```bash
echo $SSH_AUTH_SOCK
```

List public keys available through the agent:

```bash
ssh-add -L
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

* SSH agent forwarding with Vagrant
* The `config.ssh.forward_agent` configuration option
* The `SSH_AUTH_SOCK` environment variable
* Using keys loaded in the host's SSH agent
* Authentication without copying private keys to the VM
* Security considerations associated with SSH agent forwarding

The main idea is:

> **SSH agent forwarding allows a VM to use an SSH agent running on the host without transferring the private key to the VM.**
