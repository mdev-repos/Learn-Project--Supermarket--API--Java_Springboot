FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/supermarket-api-0.0.1.jar
COPY ${JAR_FILE} app_supermarketapi.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_supermarketapi.jar"]