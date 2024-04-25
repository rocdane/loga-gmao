FROM openjdk:19
EXPOSE 8001
COPY target/maintenance-service-1.0-SNAPSHOT.jar maintenance-service.jar
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]