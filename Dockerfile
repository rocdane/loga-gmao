FROM maven:3.9.5-openjdk-19
WORKDIR /home/compiler

ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]