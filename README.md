### Rodar o MySQL em container

```
docker run -d --name mysql-fiap \
    -p 3306:3306 \
    -e MYSQL_ROOT_HOST=% \
    -e MYSQL_ROOT_PASSWORD=root \
    mysql/mysql-server:5.7
```

### Rodar o Redis em container

```
docker run -d --name redis-fiap \
    -p 6379:6379 \
    redis:6.2.6-alpine
```

### Consultar chaves criadas no Redis

```
docker exec -it redis-fiap redis-cli
```
```
> KEYS *
```