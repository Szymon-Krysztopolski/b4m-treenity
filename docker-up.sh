#!/bin/bash
source .env
set -x

# Docker Compose service names
service_name="b4m-treenity"
database_service_name="b4m-treenity-database-treenity-1"
sql_content=$(< "docs/init.sql")


# Stop Docker services
docker-compose -p "$service_name" stop "$@"

# Rebuild and start Docker services
docker-compose -p "$service_name" up --build -d "$@"

# Initialize database
docker exec -it "$database_service_name" psql -U "$POSTGRES_USER" -d postgres -c "$sql_content"
