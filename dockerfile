FROM maven:3.9.12-eclipse-temurin-17-alpine AS BUILD

WORKDIR /app

COPY . .
RUN mvn clean install -DskipTest

FROM eclipse-temurin:17

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8083

CMD ["java", "-jar", "/app/app.jar"]
