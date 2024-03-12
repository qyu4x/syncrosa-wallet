FROM openjdk:17

MAINTAINER syncrosa.com

WORKDIR /app

EXPOSE 8090

COPY target/loans-service-0.0.1-SNAPSHOT.jar loans-service.jar

ENTRYPOINT ["java", "-jar", "loans-service.jar"]