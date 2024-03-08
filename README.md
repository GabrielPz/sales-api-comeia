
# Sales API

## Descrição
Este projeto é uma API REST construída com Spring Boot, utilizando JPA para persistência de dados em um banco de dados PostgreSQL. O sistema de autenticação é baseado no Keycloak. Para testes, foram utilizados JUnit e Mockito. A aplicação também está configurada para execução em containers Docker.

## Tecnologias Utilizadas
- Spring Boot
- JPA (Java Persistence API)
- Docker
- Keycloak (Autenticação)
- PostgreSQL (Banco de dados)
- JUnit (Testes)
- Swagger (Documentação da API)

## Pré-requisitos
- Docker e Docker Compose
- Java 17
- Maven
- Keycloak

## Configuração e Execução

### Keycloak
É necessário ter o Keycloak instalado e configurado. Uma maneira simples de executar o Keycloak é através do Docker. Execute o seguinte comando:
```
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:24.0.1 start-dev
```
### Docker
É necessário ter o Docker instalado. Para rodar execute o seguinte comando:
```
docker-compose up -d
```

Para verificar as instâncias do Docker pode user o seguinte comando:
```
docker ps
```

### Execução
Para realizar o run da api, execute o seguinte comando:
```
mvn spring-boot:run
```