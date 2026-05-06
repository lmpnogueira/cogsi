# =============================================================================
# provisioning/web.sh
# -----------------------------------------------------------------------------
# Purpose:
#   Web machine provisioning
#
# Demonstrates:
#   - Machine-specific provisioning
#   - Service installation
#   - Idempotence
#   - Basic web deployment
# =============================================================================

#!/bin/bash
set -e

echo "================================================="
echo "Running web machine provisioning..."
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
        sudo apt-get install -y "$PACKAGE"
    fi
}

# -----------------------------------------------------------------------------
# Install nginx
# -----------------------------------------------------------------------------
install_package nginx

# -----------------------------------------------------------------------------
# Enable and start nginx
# -----------------------------------------------------------------------------
sudo systemctl enable nginx
sudo systemctl restart nginx

# -----------------------------------------------------------------------------
# Deploy sample web page
# -----------------------------------------------------------------------------
if [ ! -f /var/www/html/index.html ] || ! grep -q "COGSI Multi-VM Web Node" /var/www/html/index.html; then
    echo "[CONFIG] Deploying demo web page..."

    cat <<EOF | sudo tee /var/www/html/index.html >/dev/null
<!DOCTYPE html>
<html>
<head>
    <title>COGSI Web VM</title>
</head>
<body>
    <h1>COGSI Multi-VM Web Node</h1>
    <p>Web server provisioning completed successfully.</p>
    <p>Try connecting to the database node (db) from inside the web VM using the hostname 'db' and IP address.</p>
</body>
</html>
EOF
fi


echo "================================================="
echo "Web machine provisioning complete!"
echo "================================================="