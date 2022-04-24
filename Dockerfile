FROM maven:3.8-adoptopenjdk-11 AS builder
COPY . .
COPY target/RD-course-management-platform-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]