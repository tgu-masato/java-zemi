# JSP / Servlet Example

**Docker / Docker Compose / Gradle / Tomcat / MySQL** を用いた **JSP / Servlet** Web アプリケーションのサンプルです。

## 必要なもの

- **Docker**

- **Docker Compose**

入ってない人たちは、 https://www.docker.com/ に行きましょう。 

## ローカルで動かすために

### **$ git clone** or ($ ghq get)

$ git clone （$ ghq get） しましょう。

``` bash
$ git clone https://github.com/cs-u-gakugei-ac-jp/java_app_sample
($ ghq get https://github.com/cs-u-gakugei-ac-jp/java_app_sample)
```

### 当該ディレクトリに移動

がんばりましょう。

``` bash
$ cd "ひとそれぞれ"/java_app_sample
```

### Docker Compose Build

下記のコマンドを実行することにより、 **MySQL コンテナ** と **Tomcat コンテナ** を作成し始めます。

```bash
$ docker-compose build
```

### Docker Compose Up

下記のコマンドを実行することにより、 **MySQL コンテナ** と **Tomcat コンテナ** が起動し、アプリケーションが動作し始めます。
また、このコマンドを実行した後に、下記の `$ docker-compose exec app gradle build` コマンドを実行すれば、自動的に変更を検知し、アプリケーションが更新されます。
プログラムを編集した際には、上記のコマンドを実行するということを覚えておきましょう。

``` bash
$ docker-compose up
```

### Gradlew Build

下記のコマンドを実行することにより、 **Tomcat コンテナ** 上で、 **build** を行い、 war ファイルが生成されます。 

``` bash
$ docker-compose exec app gradle build
```

## もろもろ確認

### Web アプリの動作確認

任意のブラウザで、 **http://localhost:8888/hello** にアクセスすれば、 "Hello World !" が表示されるはずです。

### DB の動作確認

以下のパラメータで、 DB との疎通確認が行えるはずです。データベースの指定は、任意ですがデフォルトでは以下のような **sample** という DB が生成されているはずです。

- **HOST:** 127.0.0.1
- **USER:** root
- **PASSWORD:** root
- **PORT:** 3333

## パクるために（option）

以下にパクリ方書いておきます。（バグがあったらごめんなさい。）

### .git ディレクトリの削除

``` bash
$ rm -rf .git
```

### 自身のアカウントで git リポジトリ を作成

がんばりましょう。
リポジトリを作成したら、当該リポジトリの URL をコピーしておきましょう。

### 自身の作成した git リポジトリ との関連付け

以下のコマンドを実行すれば、おそらく当該リポジトリにデータが反映されるはずです。
それからは、自分で開発頑張ってください。

``` bash
$ git remote add origin "当該リポジトリのURL" 
ex) git remote add origin https://github.com/cs-u-gakugei-ac-jp/java_app_sample.git
$ git add -A
$ git commit -m "first commit"
$ git push origin master
```
