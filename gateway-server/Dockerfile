FROM openjdk:19
EXPOSE 8765
COPY target/gateway-server-1.0-SNAPSHOT.jar gateway-server.jar
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]