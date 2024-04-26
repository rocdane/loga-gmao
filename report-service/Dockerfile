FROM openjdk:19
EXPOSE 8004
COPY target/report-service-1.0-SNAPSHOT.jar report-service.jar
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]