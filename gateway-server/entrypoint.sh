#!/bin/bash

while :
do
  status_code=$(curl --write-out %{http_code} --silent --output /dev/null http://naming-server:8761)
  status_code_config=$(curl --write-out %{http_code} --silent --output /dev/null http://cloud-server:8888/cloud-server/native)

  echo "Eureka service response: $status_code\n"
  echo "Cloud Config service response: $status_code_config"

  if [ $status_code -eq 200 ] && [ $status_code_config -eq 200 ]
  then
    java -jar gateway-server.jar
    break
  fi

  sleep 3
done