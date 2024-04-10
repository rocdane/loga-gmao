FROM maven:3.9.5
WORKDIR /home/compiler
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]