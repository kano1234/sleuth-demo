# business-web

## Run

```bash
# zipkin, elasticsearch, kibana を起動
$ docker-compose up -d

# bootRun intellij からでも良いです
$ ./gradlew bootRun

# app にリクエストしてログを出力させます
$ curl --location --request GET 'http://localhost:8080/api/v1/health'

# elasticsearch にindexができていることを確認します
$ curl --location --request GET 'http://localhost:9200/_cat/indices?v'
```

## Kibana にアクセス
https://qiita.com/zgmf_mbfp03/items/0697cc827efa89e5d93e \
上記リンクのkibana設定方法の説明がわかりやすいです

## Java version

```test
java -version
openjdk version "11.0.11" 2021-04-20
OpenJDK Runtime Environment AdoptOpenJDK-11.0.11+9 (build 11.0.11+9)
OpenJDK 64-Bit Server VM AdoptOpenJDK-11.0.11+9 (build 11.0.11+9, mixed mode)

```