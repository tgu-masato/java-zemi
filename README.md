# 中村真人のjava-semi仕様書

## 国分寺駅周辺のグルメ情報共有システム

### 改版履歴
|日付|版数|変更内容|
|:---:|:---:|:---|
|2021/05/14|初版|初版作成|
|2021/05/19|第二版|各機能を名前ごとに整理し加筆<br>改版履歴と用語の定義の追加|
|2021/05/20|第三版|データベース構造の追加|
|2021/05/22|第四版|分かりにくい用語の統一<br>データベース構造の改善①|
|2021/05/24|第五版|データベース構造の改善②|

### 用語の定義
|用語|定義|
|:---|:---|
|システム|この要求仕様書に基づいて開発する国分寺周辺のグルメ情報共有システムのこと。|
|ユーザ|本システムを利用する利用者のこと。|
|データベース|本システムを運用するために用いるMySQLのこと。|
|レビュー|ユーザが登録したおすすめのグルメ情報である、店名・評価・題名・コメントのひとまとまりのこと。|
|コメント|ユーザがレビューを登録またはレビューにリアクションするときに記入する、自由記述の文章のこと。|
|マイデータ|各ユーザが本システム上で登録またはいいねしたレビューを保存したデータのこと。|


### 1. 目的
　ユーザが国分寺駅周辺のグルメ情報を登録し、登録した自分自身や他のユーザが閲覧できることで、ユーザ間でグルメ情報を共有できるシステムを開発する。ユーザはおすすめのグルメ情報を登録・閲覧することができ、登録された情報に対してリアクション（いいね・コメント）をすることによる交流が可能である。<br>
 　これらの機能を備えたシステムを開発することで、国分寺周辺で食事をするユーザのお店選びの一助となることが期待される。


### 2. 機能要件
#### 2-1. 新規登録機能
ユーザがシステムを利用するために、システムのデータベースにユーザ情報を登録する機能。<br>
ユーザが新規登録ページにおいて必要事項を正しく入力し、ページ下部の「登録する」ボタンを押下することで、新規登録が完了する。

|入力事項|制約|備考|
|:---|:---|:---|
|ユーザネーム|NOT NULL|半角英数字|
|メールアドレス|NOT NULL, UNIQUE||
|パスワード|NOT NULL, 数字のみ不可, 英字のみ不可|半角英数字の組み合わせ|

#### 2-2. ログイン機能
ユーザが新規登録時に登録した情報を用いて、システムの2-3. 以下の機能を使用する為にログインする機能。<br>
ユーザがログインページにおいて必要事項を正しく入力し、ページ下部の「ログイン」ボタンを押下することで、ログインできる。<br>
ログイン後は、トップページに遷移する。
正しい情報が入力されなかった場合は、エラーメッセージが表示され、再度入力が求められる。

|入力事項|制約|備考|
|:---|:---|:---|
|メールアドレス|NOT NULL, UNIQUE||
|パスワード|NOT NULL, 数字のみ不可, 英字のみ不可|半角英数字の組み合わせ<br>パスワード確認も行う|

#### 2-3. レビュー登録機能
ユーザが他のユーザに共有またはマイデータに保存したいグルメ情報を登録する機能。<br>
ユーザがレビュー登録ページにおいて必要事項を入力し、ページ下部の「登録完了」ボタンを押下することで、レビューの登録が完了する。<br>
店名を新規登録する場合は、店名新規登録ページに遷移する。

|入力事項|制約|備考|
|:---|:---|:---|
|店名|いずれかを選択、または新規追加|プルダウンメニューより選択<br>店名がなければ新規追加|
|評価|0.0~5.0の中で選択|0.5刻みでの評価|
|題名|NOT NULL, 64文字以内||
|コメント|NULL可, 400文字以内||

#### 2-4. レビュー閲覧機能
ユーザが他のユーザの登録したレビューを閲覧できる機能。<br>
レビュー閲覧ページには店名ごとに、評価の平均値と最新のレビューの概要（評価と題名）が表示されている。<br>
店名を押下することで、そのお店に対するレビューの概要が一覧表示される（これをお店ブロックと呼ぶ）。<br>
さらに、お店ブロックの各レビューの概要を押下することでレビューの詳細（評価と題名とコメント）が表示される。

#### 2-5. リアクション機能
ユーザが他のユーザの登録したレビューに対して、リアクション（いいねとコメント）ができる機能。<br>
レビュー閲覧ページ以下の各表示（お店ブロック・レビューの詳細）において、「いいね」ボタンを押下することで、お店やそれに対するレビューをいいね登録できる。<br>
レビューの詳細を閲覧するページにおいて、コメント（400文字以内）で返信できる。

#### 2-6. ソート表示機能
レビュー閲覧ページ以下の各表示において、ページ下部の「ソート」ボタンを押下することで、ソートの種類に応じてレビューがソート表示される。

**ソートの種類**
- レビューされた回数順
- レビューの評価順
- レビューの更新日時順

#### 2-7. マイデータ確認機能
ユーザが自身の登録したレビューといいね登録した情報を閲覧できる機能。<br>
マイデータ確認ページの左側に、自身が登録したレビューが一覧表示される。自身が登録したレビューを選択し、再入力することで、レビューを更新できる。<br>
マイデータ確認ページの右側に、自身がいいね登録したお店またはレビューが一覧表示される。<br>
いいね登録したお店またはレビューを選択することで、お店ブロックまたはレビューの詳細が閲覧できる。

#### 2-8. ログアウト機能
ユーザがシステムを使用後に、他者に不正に使用されないようにログアウトする機能。
ログイン後の各ページ上部にある「ログアウト」ボタンを押下することで、ログアウトできる。<br>
ログアウト後は、ログインページに遷移する。


### 3. データベース構造
#### 3-1. ユーザテーブル

|カラム名|説明|型|制約|備考|
|:---|:---|:---|:---|:---|
|id|ユーザテーブルのキー|INT|UNIQUE<br>AUTO INCREMENT<br>NOT NULL||
|name|ユーザネーム|VARCHAR(20)|NOT NULL||
|email|メールアドレス|VARCHAR(100)|UNIQUE<br>NOT NULL||
|password|パスワード|VARCHAR(20)|NOT NULL||
|created_at|作成日時|DATETIME|NOT NULL||
|updated_at|更新日時|DATETIME|NOT NULL||

#### 3-2. お店テーブル

|カラム名|説明|型|制約|備考|
|:---|:---|:---|:---|:---|
|id|お店テーブルのキー|INT|UNIQUE<br>AUTO INCREMENT<br>NOT NULL||
|name|店名|VARCHAR(20)|UNIQUE<br>NOT NULL||
|evaluation_avg|評価の平均値|FLOAT|NOT NULL|ソートの参照値になる|
|review_count|ユーザからレビューされた回数|INT|NOT NULL|ソートの参照値になる|
|created_at|作成日時|DATETIME|NOT NULL||
|updated_at|更新日時|DATETIME|NOT NULL||

#### 3-3. レビューテーブル

|カラム名|説明|型|制約|備考|
|:---|:---|:---|:---|:---|
|id|レビューテーブルのキー|INT|UNIQUE<br>AUTO INCREMENT<br>NOT NULL||
|evaluation|評価|FLOAT|NOT NULL|0.0~5.0の範囲<br>0.5刻みの値<br>ソートの参照値になる|
|title|題名|VARCHAR(64)|NOT NULL||
|comment|コメント|VARCHAR(400)|||
|created_at|作成日時|DATETIME|NOT NULL||
|updated_at|更新日時|DATETIME|NOT NULL|ソートの参照値になる|
|user_id|ユーザID|INT|NOT NULL||
|store_id|お店ID|INT|NOT NULL||

#### 3-4. お店いいねテーブル

|カラム名|説明|型|制約|備考|
|:---|:---|:---|:---|:---|
|id|お店ブロックリアクションテーブルのキー|INT|UNIQUE<br>AUTO INCREMENT<br>NOT NULL||
|favorite_to_store|ユーザからのお店に対するいいねの有無|BOOLEAN|NOT NULL||
|created_at|作成日時|DATETIME|NOT NULL||
|updated_at|更新日時|DATETIME|NOT NULL||
|user_id|ユーザID|INT|NOT NULL|お店にリアクション（いいね）したユーザのユーザID|
|store_id|お店ID|INT|NOT NULL||

#### 3-5. レビューいいねテーブル

|カラム名|説明|型|制約|備考|
|:---|:---|:---|:---|:---|
|id|レビュー詳細リアクションテーブルのキー|INT|UNIQUE<br>AUTO INCREMENT<br>NOT NULL||
|favorite_to_review|ユーザからのレビューに対するいいねの有無|BOOLEAN|NOT NULL||
|created_at|作成日時|DATETIME|NOT NULL||
|updated_at|更新日時|DATETIME|NOT NULL||
|user_id|ユーザID|INT|NOT NULL|レビューにリアクション（いいね）したユーザのユーザID|
|review_id|レビューID|INT|NOT NULL||

#### 3-6. レビューコメントテーブル

|カラム名|説明|型|制約|備考|
|:---|:---|:---|:---|:---|
|id|レビュー詳細リアクションテーブルのキー|INT|UNIQUE<br>AUTO INCREMENT<br>NOT NULL||
|re_comment|返信コメント|VARCHAR(400)|||
|created_at|作成日時|DATETIME|NOT NULL||
|updated_at|更新日時|DATETIME|NOT NULL||
|user_id|ユーザID|INT|NOT NULL|レビューにリアクション（コメント）したユーザのユーザID|
|review_id|レビューID|INT|NOT NULL||

