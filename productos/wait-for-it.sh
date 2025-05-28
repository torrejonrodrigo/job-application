#!/usr/bin/env bash
#   Use this script to test if a TCP host/port are available

set -e

HOST="$1"
PORT="$2"
shift 2
TIMEOUT=60

while ! timeout 1 bash -c "echo > /dev/tcp/$HOST/$PORT" 2>/dev/null; do
  sleep 1
  TIMEOUT=$((TIMEOUT - 1))
  if [ $TIMEOUT -le 0 ]; then
    echo "Timeout waiting for $HOST:$PORT"
    exit 1
  fi
done

exec "$@"
