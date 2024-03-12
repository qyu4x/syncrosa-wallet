FROM openjdk:17

MAINTAINER syncrosa.com

WORKDIR /app

EXPOSE 8080

COPY target/users-service-0.0.1-SNAPSHOT.jar users-service.jar

ENTRYPOINT ["java", "-jar", "users-service.jar"]

