FROM amazoncorretto:17-alpine-jdk
MAINTAINER alazo
COPY target/portfolio-backend-0.0.1-SNAPSHOT.jar portfolioback.jar
ENTRYPOINT ["java","-jar","/portfolioback.jar"]

