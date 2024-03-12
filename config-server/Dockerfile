FROM openjdk:17

MAINTAINER syncrosa.com

WORKDIR /app

EXPOSE 8888

COPY target/config-server-0.0.1-SNAPSHOT.jar config-server.jar

ENTRYPOINT ["java", "-jar", "config-server.jar"]