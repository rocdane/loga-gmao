FROM openjdk:19
EXPOSE 8006
COPY target/part-service-1.0-SNAPSHOT.jar part-service.jar
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]