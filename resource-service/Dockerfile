FROM openjdk:19
EXPOSE 8005
COPY target/resource-service-1.0-SNAPSHOT.jar resource-service.jar
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]