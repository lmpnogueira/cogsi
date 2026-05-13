from flask import Flask
import os
import socket
from datetime import datetime

app = Flask(__name__)

LOG_FILE = "/data/access.log"

@app.route("/")
def hello():
    with open(LOG_FILE, "a") as f:
        f.write(f"{datetime.now()} - Accessed\n")

    return {
        "message": "Hello from Docker!",
        "hostname": socket.gethostname(),
        "version": os.getenv("APP_VERSION", "undefined")
    }

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8000)