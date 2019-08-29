# kafka #

## Introduction ##

## Quickstart ##

#### 常用 ####

cd /usr/workplace
tar -zxvf kafka_2.12-2.3.0.tgz

#### Base ####

1. wget http://mirror.bit.edu.cn/apache/kafka/2.3.0/kafka_2.12-2.3.0.tgz
2. tar -zxvf kafka_2.12-2.3.0.tgz
3. cd kafka_2.12-2.3.0

#### server ####

1. bin/zookeeper-server-start.sh config/zookeeper.properties
2. bin/kafka-server-start.sh config/server.properties

#### topic ####

1. bin/kafka-topics.sh --list --bootstrap-server localhost:9092

#### Send some messages ####

#### Start a consumer ####

