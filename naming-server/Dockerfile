FROM openjdk:19
EXPOSE 8761
COPY target/naming-server-1.0-SNAPSHOT.jar naming-server.jar
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]