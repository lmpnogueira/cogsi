# Module: Observability 

This directory contains the configuration files and dashboards required for the Observability module. The content is divided into two distinct stages to help you understand the evolution from simple data collection to a professional monitoring stack.

---

## 📂 Repository Structure

### 1. `/prometheus`
**Used in: Lecture 1 and 2 (Introduction to Metrics)**
This folder focuses exclusively on **Prometheus** and **Node Exporters**. 
- **Goal:** Understand how Prometheus scrapes metrics and how to write basic PromQL queries, and how to define Recording Rules.
- **Key Files:** - `prometheus.yml`: Main configuration for the scraper.
    - `cpu-node-rules.yml`: Contains the basic recording rules.

### 2. `/prometheus_alertmanager_grafana`
**Used in: Lecture 2 (The Full Stack)**
This folder explores the integration of the three core tools: **Prometheus**, **Alertmanager**, and **Grafana**.
- **Goal:** Build a complete end-to-end pipeline where metrics are collected, alerts are routed, and data is visualized.
- **Key Files:**
    - Integrated configuration for all three services.
    - Alertmanager routing rules.
    - Dashboard JSON files for Grafana.

---

## 📊 Grafana Dashboards

Inside the second folder, you will find `.json` files for the Grafana dashboards. You can import these directly into your Grafana instance:

1. **Static Dashboard**: A fixed view focusing on cluster-wide health. Ideal for understanding how Recording Rules are visualized.
2. **Dynamic Dashboard**: A parameterized view using variables (`job`, `group`, `instance`). This allows you to filter and drill down into specific nodes or environments.

### How to Import:
1. Open Grafana in your browser (usually `http://localhost:3000`).
2. Navigate to **Dashboards** -> **New** -> **Import**.
3. Upload the `.json` file or paste the JSON content.
4. Select your **Prometheus** data source and click **Import**.

---

## 🛠 Prerequisites
- **Node Exporter**: Ensure it is running on your target machines (Linux or macOS).
- **Prometheus**: Installed and configured to read from the provided `.yml` files.
- **Alertmanager**: Installed and configured to read from the provided `.yml` files.
- **Grafana**: Installed.

