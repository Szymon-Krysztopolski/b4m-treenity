FROM maven:3.9.2-eclipse-temurin-17-alpine as builder
COPY src /src
COPY pom.xml /
RUN mvn clean install

FROM eclipse-temurin:17-alpine as server
ARG JAR_FILE=target/*.jar
COPY --from=builder ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
