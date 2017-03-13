# https://hub.docker.com/_/openjdk/
FROM openjdk:8-jre

EXPOSE 8000

COPY java9-link.jar /home/server/

WORKDIR /home/server
CMD ["java", "-jar", "java9-link.jar", "8000"]
