#!/bin/sh
PASS='"$MYSQL_ROOT_PASSWORD"'
PREFIX='docker exec mysql-develop sh -c'

if [ "x$1" = "x" ]
then
    docker exec mysql-develop sh -c 'exec mysqldump --all-databases -uroot -p"$MYSQL_ROOT_PASSWORD"' > backups/Backup-ALL.sql
else 
    docker exec mysql-develop sh -c 'exec mysqldump '$1' -uroot -p"$MYSQL_ROOT_PASSWORD"' > backups/Backup-$1.sql
fi

