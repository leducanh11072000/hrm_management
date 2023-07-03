FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn verify clean --fail-never
COPY src src
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17
COPY --from=build /app/target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
