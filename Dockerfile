# Étape 1 : Builder l'application avec Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : Exécuter l'application avec OpenJDK
FROM openjdk:21-jdk-slim
LABEL maintainer="Minhaj"
WORKDIR /app
COPY --from=builder /app/target/metsetmerveilles-*.war metsetmerveilles.war
EXPOSE 8080
CMD ["java", "-jar", "/app/metsetmerveilles.war"]