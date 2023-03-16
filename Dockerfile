FROM amazoncorretto:17-alpine-jdk
MAINTAINER alazo
COPY target/portfolio-backend-0.0.1-SNAPSHOT.jar al-app.jar
ENTRYPOINT ["java","-jar","/al-app.jar"]
