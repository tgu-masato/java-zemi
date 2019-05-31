#!/bin/bash

pwd_dir=`pwd`
current_dir=`basename $pwd_dir`

if [ $current_dir = java_app_sample ]; then
  echo "* stoping ..."
else
  echo "* java_app_sample ディレクトリをカレントディレクトリとして実行してください。"
  exit
fi

echo "\n* docker-compose up -d\n"
docker-compose down

