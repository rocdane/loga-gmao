FROM openjdk:19
EXPOSE 8006
COPY target/finance-service-1.0-SNAPSHOT.jar finance-service.jar
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]