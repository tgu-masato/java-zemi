#!/bin/bash

pwd_dir=`pwd`
current_dir=`basename $pwd_dir`

if [ $current_dir = java_app_sample ]; then
  echo "* starting ..."
else
  echo "* java_app_sample ディレクトリをカレントディレクトリとして実行してください。"
  exit
fi

echo "\n* docker-compose up -d\n"
docker-compose up -d

echo "\n* docker-compose exec app gradle -t build"
docker-compose exec -d app gradle -t build

echo "\n* docker-compose ps\n"
docker-compose ps

