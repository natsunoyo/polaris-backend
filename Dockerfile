FROM maven:3.9.9-amazoncorretto-21-debian AS build
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:21
COPY --from=build /target/polaris-backend-0.0.1-SNAPSHOT.jar PolarisJobPortal.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "PolarisJobPortal.jar"]