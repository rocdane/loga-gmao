FROM openjdk:19
EXPOSE 8888
WORKDIR /opt
COPY config ./config
COPY target/cloud-server-0.0.1-SNAPSHOT.jar /opt
ENTRYPOINT ["java","-jar","./cloud-server-0.0.1-SNAPSHOT.jar"]