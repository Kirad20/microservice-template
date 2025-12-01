#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    SELECT 'CREATE DATABASE user_db' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'user_db')\gexec
    SELECT 'CREATE DATABASE product_db' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'product_db')\gexec
    SELECT 'CREATE DATABASE client_db' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'client_db')\gexec
    SELECT 'CREATE DATABASE appointment_db' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'appointment_db')\gexec
EOSQL
