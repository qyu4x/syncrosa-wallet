FROM openjdk:17

MAINTAINER syncrosa.com

WORKDIR /app

EXPOSE 9000

COPY target/cards-service-0.0.1-SNAPSHOT.jar cards-service.jar

ENTRYPOINT ["java", "-jar", "cards-service.jar"]