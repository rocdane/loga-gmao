FROM openjdk:19
EXPOSE 8888
COPY config/* /config/
COPY target/cloud-server.jar cloud-server.jar
ENTRYPOINT ["java","-jar","cloud-server.jar"]