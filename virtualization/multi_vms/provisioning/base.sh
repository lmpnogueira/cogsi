# =============================================================================
# provisioning/base.sh
# -----------------------------------------------------------------------------
# Purpose:
#   Global provisioning script applied to ALL VMs
#
# Demonstrates:
#   - Shared base provisioning
#   - Idempotent package installation
#   - Reusable shell functions
#   - Common tooling setup
#
# Notes:
#   This script should remain generic so it can support:
#   - web
#   - db
#   - future machines (cache, worker, db_follower, etc.)
# =============================================================================

#!/bin/bash
set -e

echo "================================================="
echo "Running global base provisioning..."
echo "================================================="

# -----------------------------------------------------------------------------
# Update package metadata
# -----------------------------------------------------------------------------
sudo apt-get update

# -----------------------------------------------------------------------------
# Idempotent package installer
# -----------------------------------------------------------------------------
install_package() {
    PACKAGE=$1

    if dpkg -s "$PACKAGE" >/dev/null 2>&1; then
        echo "[OK] $PACKAGE is already installed."
    else
        echo "[INSTALL] Installing $PACKAGE..."
        sudo apt-get install -y "$PACKAGE"
    fi
}

# -----------------------------------------------------------------------------
# Core packages for all VMs
# -----------------------------------------------------------------------------
install_package git
install_package curl
install_package nano
install_package net-tools

# -----------------------------------------------------------------------------
# Shared folder marker
# -----------------------------------------------------------------------------
if [ -d /shared ]; then
    if [ ! -f /shared/base_provisioned.txt ]; then
        echo "Base provisioning completed on $(date)" | sudo tee /shared/base_provisioned.txt >/dev/null
    fi
fi

echo "================================================="
echo "Global base provisioning complete!"
echo "================================================="