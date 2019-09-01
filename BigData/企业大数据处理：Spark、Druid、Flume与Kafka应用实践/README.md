# 企业大数据处理 Spark、Druid、Flume与Kafka应用实践 #

第一部分 准备工作

## 第1章 基础环境准备 ##

### 1.1 软件环境准备 ###

#### 1. JDK安装 ####

#### 2. Maven安装 ####

#### 3. Scala安装 ####

### 1.2 集群环境准备 ###

#### 1.2.1 Zookeeper集群部署 ####

**1. 集群规划**

FastLeaderElection算法选举Leader，集群中过半的机器正常运行才能成功选举Leader，集群部署的节点数为奇数个，最少节点个数为3.

生产环境建议部署5个以上的奇数个节点，因为3个实例其中只要有一个实例不可用，整个Zookeeper集群将无法成功选举。

**2. 部署过程**

	1. 下载安装包并解压
		wget http://apache.fayea.com/zookeeper/zookeeper-3.4.6/zookeeper-3.4.6.tar.gz
		解压到/data/soft目录下
		tar -zxvf http://apache.fayea.com/zookeeper/zookeeper-3.4.6/zookeeper-3.4.6.tar.gz -C /data/soft
	2. 创建软连接
		创建软连接便于以后升级版本
	ls -s /data/soft/zookeeper-3.4.6 /usr/local/zookeeper
	3. 设置环境变量
		vim /etc/profile
		export ZOOKEEPER_HOME=/usr/local/zookeeper
		export PATH=$PATH: $JAVA_HOME/bin:$M2_HOM/bin:$SCALA_HOME/bin: $ZOOKEEKER_HOME/bin
		刷新环境变量使其生效： Source/etc/profile
	4. 配置
		进入到Zookeeper安装目录： cd /usr/local/zookeeper
		拷贝一份conf目录下的配置文件，重命名为zoo.cfg: cp .conf/zoo_sample.cfg ./conf/zoo.cfg


