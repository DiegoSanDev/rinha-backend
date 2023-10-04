FROM openjdk:17-jdk-slim-buster

WORKDIR /app

COPY target/rinha-backend-0.0.1-SNAPSHOT.jar /app/rinha.jar

EXPOSE 80

ENTRYPOINT ["java","-jar","rinha.jar"]