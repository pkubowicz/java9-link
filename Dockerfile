# https://hub.docker.com/_/openjdk/
FROM openjdk:8-jre

EXPOSE 8000

COPY greeter-server-all.jar /home/server/

WORKDIR /home/server
CMD ["java", "-jar", "greeter-server-all.jar", "8000"]
