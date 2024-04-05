FROM openjdk:19
EXPOSE 8761
WORKDIR /opt
COPY target/naming-server-0.0.1-SNAPSHOT.jar /opt
ENTRYPOINT ["java","-jar","./naming-server-0.0.1-SNAPSHOT.jar"]