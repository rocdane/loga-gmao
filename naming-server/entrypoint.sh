#!/bin/bash

while :
do
  status_code=$(curl --write-out %{http_code} --silent --output /dev/null http://cloud-server:8888/cloud-server/native)

  echo "Cloud Config service response: $status_code"

  if [ $status_code -eq 200 ]
  then
      java -jar naming-server.jar
      break
  fi

  sleep 3
done