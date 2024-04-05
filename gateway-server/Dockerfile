FROM openjdk:19
EXPOSE 8765
COPY target/gateway-server-0.0.1-SNAPSHOT.jar /opt
WORKDIR /opt
ENTRYPOINT ["java","-jar","./gateway-server-0.0.1-SNAPSHOT.jar"]
