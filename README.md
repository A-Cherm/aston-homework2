### Сервисы

localhost:

- 8080: user service
- 8081: eureka server
- 8082: gateway
- 8888: config server

Из сервера конфигурации подгружается в _user-service_ 
только тестовый параметр _config.test_.

### OpenApi

http://localhost:8080/swagger-ui/index.html

### Параметры БД

- url: localhost:5435/postgres
- user: postgres
- pass: postgres

### Параметры Kafka

- nodes: 1
- host: localhost:9092

Можно поднять в контейнерах через

> docker compose up -d