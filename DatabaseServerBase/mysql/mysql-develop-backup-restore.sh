#!/bin/sh
PASS='"$MYSQL_ROOT_PASSWORD"'
PREFIX='docker exec mysql-develop sh -c'

if [ "x$1" = "x" ]
then
    echo "Pass path to created backup"
elif [ "x$1" = "xALL" ]
then
    echo "Restoring all DBs"
    docker exec -i mysql-develop sh -c 'exec mysql -uroot -p"$MYSQL_ROOT_PASSWORD"' < backups/Backup-$1.sql
else 
    echo "Restoring only $1"
    docker exec -i mysql-develop sh -c 'exec mysql -uroot -p"$MYSQL_ROOT_PASSWORD" '$1 < backups/Backup-$1.sql
fi

