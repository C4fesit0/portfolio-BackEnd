FROM amazoncorretto:17-alpine-jdk
MAINTAINER alazo
COPY target/portfolio-backend-0.0.1-SNAPSHOT.jar portbackend.jar
ENTRYPOINT ["java","-jar","/portbackend.jar"]

