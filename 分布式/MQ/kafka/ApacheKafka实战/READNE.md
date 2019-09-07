# Apache Kafka #

### 3.2 伪分布式环境安装 ###

#### 3.2.1 安装Java ####

#### 3.2.2 安装Zookeeper ####

#### 3.2.3 安装单节点Kafka集群 ####

### 3.3 多节点环境安装 ###

#### 3.3.1 安装多节点ZooKeeper集群 ####

#### 3.3.2 安装多节点Kafka ####



### 3.4 验证部署 ###

#### 3.4.1 测试topic创建与删除 ####

在开始使用Kafka集群前最好把所需的topic创建出来，并执行对应的命令做验证。

#### 3.4.2 测试消息发送与消费 ####

默认提供的kafka-console-producer和kafka-console-consumer脚本。

#### 3.4.3 生产者吞吐量测试 ####

kafka-producer-pref-test脚本

#### 3.4.4 消费者吞吐量测试 ####

kafka-comsumer-pref-test脚本

### 3.5 参数设置 ###

* broker端参数
* topic级别参数
* GC配置参数
* JVM参数
* OS参数

#### 3.5.1 broker端参数 ####

broker端参数需要在Kafka目录下的config/server.properties文件中进行设置。

Kafka不支持动态修改，需要重启对应的broker的服务器

#### 3.5.2 topic级别参数 ####

每个不同的topic都可以设置自己的参数值。

#### 3.5.3 GC参数 ####

G1垃圾收集器

#### 3.5.4 JVM参数 ####

#### 3.5.5 OS参数 ####

### 3.6 本章小结 ###

## 第4章 producer开发 ##

### 4.1 producer概览 ###

### 4.2 构造producer ###

#### 4.2.1 producer程序实例 ####

**1. 构造Properties对象**

[http://www.ishenping.com/ArtInfo/1426033.html](http://www.ishenping.com/ArtInfo/1426033.html)

*bootstrap.servers*

指定一组host:port,用于创建向Kafka broker服务器的连接，比如k1:9092，k2:9092, k3:9092

producer使用时需要替换成实际的broker列表。

如果broker端没有显式配置listeners使用IP地址，最好将该参数配置成主机名，而不是IP地址（FQDN）

*key.serializer*

被发送到broker端的任何消息的格式都必须是字节数组。生产者接口允许



*value.serializer*

与key.serializer一样，value.serializer指定的类会将值序列化。如果键和值都是字符串，可以使用
