from flask import Flask
import os
import socket
import redis
from datetime import datetime

app = Flask(__name__)

LOG_FILE = "/data/access.log"

redis_client = redis.Redis(
    host=os.getenv("REDIS_HOST", "localhost"),
    port=int(os.getenv("REDIS_PORT", "6379")),
    decode_responses=True
)


@app.route("/")
def hello():
    with open(LOG_FILE, "a") as f:
        f.write(f"{datetime.now()} - Accessed\n")

    visits = redis_client.incr("visits")

    return {
        "message": "Hello from Docker!",
        "hostname": socket.gethostname(),
        "version": os.getenv("APP_VERSION", "undefined"),
        "visits": visits
    }


@app.route("/health")
def health():
    try:
        redis_client.ping()
        return {"status": "healthy"}, 200
    except redis.RedisError:
        return {"status": "unhealthy"}, 503


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)