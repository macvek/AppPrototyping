#!/bin/sh
docker run --rm --network devnet -v mysql-develop-storage:/var/lib/mysql --name mysql-develop -e MYSQL_ROOT_PASSWORD=1234 -i -t mysql:8.0.31
