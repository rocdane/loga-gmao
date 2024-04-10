FROM openjdk:19
EXPOSE 8001
COPY target/authentication-service.jar authentication-service.jar
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]