# 图解Kafka之实战指南 #

# 初识Kafka #

* 消息系统
* 存储系统
* 流式处理平台

## 基本概念 ##

一个典型的Kafka体系架构包括若干Producer、若干Broker、若干Consumer，以及一个ZooKeeper集群。

1. Producer：生产者，也就是发送消息的一方。生产者负责创建消息，然后将其投递到Kafka中。
2. Consumer：消费者，也就是接收消息的一方。消费者连接到Kafka上接收消息，进而进行相应的业务逻辑处理。
3. Broker：服务代理节点。

在Kafka中，主题（Topic）与分区（Partition）。

offset

# Kafka入门 #

## 安装与配置 ##

### 1. JDK的安装与配置 ###


	ll jdk-8u181-linux-x64.tar.gz 

	tar zxvf jdk-8u181-linux-x64.tar.gz 

	cd jdk1.8.0_181

	jdk1.8.0_181]# pwd


### 2. ZooKeeper安装与配置 ###

### 3. Kafka的安装与配置 ###

	ll kafka_2.11-2.0.0.tgz 

	tar zxvf kafka_2.11-2.0.0.tgz

	cd kafka_2.11-2.0.0

## 生产与消费 ##

