#!/bin/bash

BASE_DIR="$(dirname "$(realpath "$0")")"
source "$BASE_DIR"/../.env

# Docker Compose service names
database_service_name="treenity-database-container"
sql_content=$(< "$BASE_DIR/init.sql")

# Variable encoding fix
POSTGRES_USER=$(echo "$POSTGRES_USER" | tr -d '\r')

# Initialize database
sleep 1 # Wait for docker of database
docker exec -it "$database_service_name" psql -U "$POSTGRES_USER" -d postgres -c "$sql_content"

read -r -p "Press any key to resume ..."
