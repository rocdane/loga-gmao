FROM openjdk:19
EXPOSE 8888
COPY config/* /config/
COPY target/cloud-server-1.0-SNAPSHOT.jar cloud-server.jar
ENTRYPOINT ["java","-jar","cloud-server.jar"]