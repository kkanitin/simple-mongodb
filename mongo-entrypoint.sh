#!/usr/bin/env bash

# Run MongoDB with authentication enabled
#mongod --auth

# If a MongoDB initialization script is provided, execute it
if [ -f /docker-entrypoint-initdb.d/mongo-init.js ]; then
    echo "Executing MongoDB initialization script..."
    mongosh --authenticationDatabase admin -u "$MONGO_INITDB_ROOT_USERNAME" -p "$MONGO_INITDB_ROOT_PASSWORD" < /docker-entrypoint-initdb.d/mongo-init.js
    echo "MongoDB initialization script executed successfully."
fi

# Keep the container running
tail -f /dev/null