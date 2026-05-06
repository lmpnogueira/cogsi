#!/bin/bash
# =============================================================================
# bootstrap.sh — COGSI / Virtualization Part I
# -----------------------------------------------------------------------------
# Purpose:
#   Basic provisioning script for the lecture repository
#
# Demonstrates:
#   - Shell provisioning
#   - Idempotence
#   - Package management
#   - Conditional logic with environment variables
#   - Basic service setup
#
# IMPORTANT:
#   This script is intentionally simple and heavily commented so students can:
#   - Read it easily
#   - Modify it safely
#   - Extend it in future exercises
# =============================================================================

set -e

echo "================================================="
echo "Starting VM provisioning..."
echo "================================================="

# -----------------------------------------------------------------------------
# Update package metadata
# -----------------------------------------------------------------------------
sudo apt-get update

# -----------------------------------------------------------------------------
# Install core tools (idempotent via dpkg check)
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

# Core tools for all environments
install_package git
install_package curl
install_package nano

# -----------------------------------------------------------------------------
# Optional package installation via environment variable
# Host example:
#   export INSTALL_NGINX=true
# -----------------------------------------------------------------------------
if [ "$INSTALL_NGINX" = "true" ]; then
    install_package nginx

    # Enable and start service
    sudo systemctl enable nginx
    sudo systemctl restart nginx

    # -------------------------------------------------------------------------
    # Create a simple custom homepage for demonstration
    # -------------------------------------------------------------------------
    if [ ! -f /var/www/html/index.html ] || ! grep -q "COGSI Virtualization Demo" /var/www/html/index.html; then
        echo "[CONFIG] Creating demo web page..."
        cat <<EOF | sudo tee /var/www/html/index.html >/dev/null
<!DOCTYPE html>
<html>
<head>
    <title>COGSI Virtualization Demo</title>
</head>
<body>
    <h1>COGSI Virtualization Demo</h1>
    <p>Provisioning completed successfully!</p>
    <p>This page is served from inside the Vagrant VM.</p>
</body>
</html>
EOF
    fi
fi

# -----------------------------------------------------------------------------
# Shared folder demonstration
# -----------------------------------------------------------------------------
if [ -d /shared ]; then
    echo "[INFO] Shared folder detected at /shared"

    # Create a sample file only if it doesn't already exist
    if [ ! -f /shared/provisioned.txt ]; then
        echo "This file was created by bootstrap.sh on $(date)" > /shared/provisioned.txt
    fi
fi

echo "================================================="
echo "Provisioning complete!"
echo "================================================="