#!/bin/bash
# =============================================================================
# provisioning/setup.sh
# -----------------------------------------------------------------------------
# Purpose:
#   Install the SSH client and provide a simple environment for demonstrating
#   SSH agent forwarding.
# =============================================================================

set -e

echo "================================================="
echo "Setting up SSH agent forwarding demo..."
echo "================================================="

apt-get update
apt-get install -y openssh-client

echo
echo "SSH client installed."
echo
echo "After connecting to the VM, check the forwarded agent with:"
echo
echo "    echo \$SSH_AUTH_SOCK"
echo "    ssh-add -L"
echo
echo "================================================="