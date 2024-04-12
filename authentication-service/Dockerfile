FROM openjdk:19
EXPOSE 8001
COPY target/authentication-service-1.0-SNAPSHOT.jar authentication-service.jar
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]