FROM amazoncorretto:17
MAINTAINER Aiau
COPY backend.jar projectsoftwarebackend.jar
ENTRYPOINT ["java", "-jar", "projectsoftwarebackend.jar"]