#!/bin/bash
set -x

# Docker Compose service names
service_name="b4m-treenity"
database_service_name="database-treenity"

# Stop Docker services
docker-compose -p "$service_name" stop "$@"

# Rebuild and start Docker services
docker-compose -p "$service_name" up --build -d "$@"

