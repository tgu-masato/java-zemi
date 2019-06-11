#!/bin/bash

if [[ `which ghq` = "" ]]; then
    echo "* ghq コマンドがインストールされていません。このファイルを実行するには、ghq をインストールしてください。"
    exit
fi

if [[ `ls -la | grep .git` = "" ]]; then
    echo '* "$ sh bin/regenerate.sh" のようにして、java_app_sample リポジトリの直下で実行してください。'
    exit
fi
java_app_sample_dir=`pwd`

echo "* はじめに、このプロジェクトを置くための空の GitHub リポジトリを用意してください。"
echo "* すでに GitHub リポジトリを用意できていますか？ [y / n]"
while :
do
  read key
  if [[ ${key} = "y" ]] || [[ ${key} = "n" ]]; then
    break
  else
    echo "* 小文字の y or n を入力してください。"
  fi
done
if [[ ${key} = "n" ]]; then
    exit
fi

while :
do
  echo "\n* 作成した GitHub リポジトリの URL を入力してください。"
  echo "* ex) https://github.com/cs-u-gakugei-ac-jp/java_app_sample"
  read repository_name
  echo "* リポジトリの URL は、 ${repository_name} でよろしいですか？ [y / n]"
  read key
  if [[ ${key} = "y" ]]; then
    break
  elif  [[ ${key} = "n" ]]; then
    continue
  else
    echo "* 小文字の y or n を入力してください。"
  fi
done

echo "\n* ghq get ${repository_name}"
ghq get ${repository_name}

new_repository_dir=`ghq root`/"${repository_name/https:\/\//}"

if [[ `ls ${new_repository_dir}` != "" ]]; then
    echo "* ${repository_name} には、すでにファイルが存在しています。空の GitHub リポジトリを用意してください。"
    exit
fi

echo "\n* java_app_sample ディレクトリの内容をコピーしています。"
cp -rf ${java_app_sample_dir}/. ${new_repository_dir}/
echo "* done!"

echo '\n* 不必要なファイルを削除しています。'
if [[ `which rmtrash` = "" ]]; then
  rm -rf ${new_repository_dir}/bin
  rm -rf ${new_repository_dir}/.git
  rm -rf ${new_repository_dir}/build
  rm ${new_repository_dir}/.env
else
  rmtrash ${new_repository_dir}/bin
  rmtrash ${new_repository_dir}/.git
  rmtrash ${new_repository_dir}/build
  rmtrash ${new_repository_dir}/.env
fi
echo "* done!"

echo "\n* .env ファイルを作成します。"
project_name=`basename ${new_repository_dir}`
cp ${new_repository_dir}/.env.sample ${new_repository_dir}/.env

while :
do
  echo "* ローカルから DB コンテナと疎通を行うポート番号を入力してください。（ポート番号は、既に使用しているものとかぶらないようにしましょう。）"
  echo "ex) 3333"
  read db_port
  echo "* DB コンテナと疎通を行うポート番号は、 ${db_port} でよろしいですか？ [y / n]"
  read key
  if [[ ${key} = "y" ]]; then
    break
  elif  [[ ${key} = "n" ]]; then
    continue
  else
    echo "* 小文字の y or n を入力してください。"
  fi
done

while :
do
  echo "\n* ローカルから Tomcat コンテナと疎通を行うポート番号を入力してください。（ポート番号は、既に使用しているものとかぶらないようにしましょう。）"
  echo "ex) 8888"
  read app_port
  echo "* Tomcat コンテナと疎通を行うポート番号は、 ${app_port} でよろしいですか？ [y / n]"
  read key
  if [[ ${key} = "y" ]]; then
    break
  elif  [[ ${key} = "n" ]]; then
    continue
  else
    echo "* 小文字の y or n を入力してください。"
  fi
done

while :
do
  echo "\n* デフォルトで生成する DB の名前を入力してください。"
  read db_name
  echo "* デフォルトで生成する DB の名前は、 ${db_name} でよろしいですか？ [y / n]"
  read key
  if [[ ${key} = "y" ]]; then
    break
  elif  [[ ${key} = "n" ]]; then
    continue
  else
    echo "* 小文字の y or n を入力してください。"
  fi
done

perl -pi -e \
   "s|PROJECT_NAME=|PROJECT_NAME=$project_name|g" \
   ${new_repository_dir}/.env
perl -pi -e \
   "s|DB_PORT=|DB_PORT=$db_port|g" \
   ${new_repository_dir}/.env
perl -pi -e \
   "s|APP_PORT=|APP_PORT=$app_port|g" \
   ${new_repository_dir}/.env
perl -pi -e \
   "s|DB_NAME=|DB_NAME=$db_name|g" \
   ${new_repository_dir}/.env
echo "* done!"

echo "\nREADME.md の初期化を行います。"
rm ${new_repository_dir}/README.md
touch ${new_repository_dir}/README.md
echo "#${project_name}" >> ${new_repository_dir}/README.md
echo "* done!"

echo "\n* GitHub の諸設定を行います。"
cd ${new_repository_dir}
echo "* $ git init"
git init
echo "* $ git remote add origin ${repository_name}.git"
git remote add origin ${repository_name}.git
echo "* $ git add -A"
git add -A
echo '* $ git commit -m "first commit (reference: https://github.com/cs-u-gakugei-ac-jp/java_app_sample)'
git commit -m "first commit (reference: https://github.com/cs-u-gakugei-ac-jp/java_app_sample)"
echo "* $ git push origin master"
git push origin master
echo "* done!"

echo "\nセットアップが完了しました。以下の手順に則ると、開発を始められます。"
echo "\n1. $ ghq look ${project_name} ( 当該ディレクトリに移動する。 )"
echo "2. $ docker-compose build ( イメージのビルドを行う。 )"
echo "3. $ docker-compose up ( コンテナの起動を行う。 )"
echo "4. http://localhost:${app_port} にブラウザ等でアクセスし、アプリケーションが動作することを確認する。"
echo "5. 以下の情報で、DB にアクセスし、疎通確認を行う。 ( TablePlus 等を用いるとよいのかな。 )"
echo "  - User: root"
echo "  - Password: root"
echo "  - Port: ${db_port}"
echo "  - DB名: ${db_name}"
