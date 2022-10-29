#!/bin/bash
docker run --rm -p 10001:80 --network devnet -e PMA_HOST=mysql-develop -i -t --name phpmyadmin phpmyadmin