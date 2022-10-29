#!/bin/sh
docker run --rm --network devnet -p 3306:3306 -v mysql-develop-storage:/var/lib/mysql --name mysql-develop -e MYSQL_ROOT_PASSWORD=1234 -i -t  mysql:8.0.31
