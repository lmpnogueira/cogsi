# =============================================================================
# provisioning/db.sh
# -----------------------------------------------------------------------------
# Purpose:
#   Database machine provisioning
#
# Demonstrates:
#   - Machine-specific provisioning
#   - Service setup
#   - Non-interactive installs
#   - Placeholder DB node for multi-machine topology
#
# =============================================================================

#!/bin/bash
set -e

echo "================================================="
echo "Running database machine provisioning..."
echo "================================================="

# -----------------------------------------------------------------------------
# Idempotent package installer
# -----------------------------------------------------------------------------
install_package() {
    PACKAGE=$1

    if dpkg -s "$PACKAGE" >/dev/null 2>&1; then
        echo "[OK] $PACKAGE is already installed."
    else
        echo "[INSTALL] Installing $PACKAGE..."
        sudo DEBIAN_FRONTEND=noninteractive apt-get install -y "$PACKAGE"
    fi
}

# -----------------------------------------------------------------------------
# Install MariaDB server
# -----------------------------------------------------------------------------
install_package mariadb-server

# -----------------------------------------------------------------------------
# Enable and start MariaDB
# -----------------------------------------------------------------------------
sudo systemctl enable mariadb
sudo systemctl restart mariadb

# -----------------------------------------------------------------------------
# Create simple marker for demonstration
# -----------------------------------------------------------------------------
if [ ! -f /home/vagrant/db_node_ready.txt ]; then
    echo "Database node provisioned on $(date)" > /home/vagrant/db_node_ready.txt
    sudo chown vagrant:vagrant /home/vagrant/db_node_ready.txt
fi

echo "================================================="
echo "Database machine provisioning complete!"
echo "================================================="