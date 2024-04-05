case $1 in
  "build")

    echo "creating compiler container"

    docker build . -t loga-compiler

    docker run -v $(pwd):/home/compiler loga-compiler

    docker rmi loga-compiler --force

    echo "compiled!"

  ;;
  "run")
    docker-compose -p "loga" up -d --force-recreate --build
;;
esac