# Sobre
Esse é um projeto inspirado em um torneio chamado **Rinha de Backend** idealizado por Francisco Zanfranceschi. 
Maiores detalhes na página do [GitHub](https://github.com/zanfranceschi/rinha-de-backend-2023-q3/blob/main/INSTRUCOES.md). Achei muito legal a ideia! :)

Não participei do torneio, mas resolvi fazer uma versão em Java porque achei muito interessante e pude aprender melhor o funcionamento do docker, por exemplo.

O projeto foi desenvolvido seguindo as regras da [Rinha de Backend](https://github.com/zanfranceschi/rinha-de-backend-2023-q3/blob/main/INSTRUCOES.md)

## Tecnologias utilizadas:
- Maven
- Java 17
- Spring Boot
- JPA/Hibernate
- PostgreSQL
- Docker
- Docker Compose

# Necessário
- Maven 3.9.0
- Java 17
- Docker 20.10.24
- Docker Compose 1.25.0

# Build

### Em um terminal executar respectivamente os comandos abaixo:

1. **mva clean install** ou **mvn clean package**
2. **docker build -t rinha .**
3. **sudo docker-compose up --build**
