FROM openjdk:19
EXPOSE 8002
COPY target/customer-service-1.0-SNAPSHOT.jar customer-service.jar
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]