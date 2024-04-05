FROM maven:3.9.6-ibmjava
WORKDIR /home/compiler
ADD entrypoint.sh entrypoint.sh
CMD [ "entrypoint.sh" ]
ENTRYPOINT ["sh"]