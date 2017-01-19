#!/bin/sh

die() {
    echo "Shutting down"
    kill $server
}

trap "die" SIGHUP SIGINT SIGTERM

java -jar target/dropwizard-sse-0.1.jar server config.yml & server=$!
./caddy
