FROM maven:3.9.6-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21.0.7-jdk
COPY --from=build /target/polaris-backend-0.0.1-SNAPSHOT.jar PolarisJobPortal.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "PolarisJobPortal.jar"]