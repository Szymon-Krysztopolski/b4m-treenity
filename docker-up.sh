#!/bin/bash
source .env
set -x

# Stop Docker services
docker compose stop "$@"

# Rebuild and start Docker services
docker compose up --build -d "$@"

#-------------------------------------------------------------------------
# TODO - check initialization of database

# Docker Compose service names
database_service_name="b4m-treenity-database-treenity-1"
sql_content=$(< "init.sql")

# Variable encoding fix
POSTGRES_USER=$(echo "$POSTGRES_USER" | tr -d '\r')

# Initialize database
sleep 1 # Wait for docker of database
docker exec -it "$database_service_name" psql -U "$POSTGRES_USER" -d postgres -c "$sql_content"
