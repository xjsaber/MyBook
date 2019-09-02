# 企业大数据处理 Spark、Druid、Flume与Kafka应用实践 #

# 第一部分 准备工作 #

## 第1章 基础环境准备 ##

### 1.1 软件环境准备 ###

#### 1. JDK安装 ####

	yum install -y java

#### 2. Maven安装 ####

	yum install -y maven

#### 3. Scala安装 ####

	wget http://downloads.lightbend.com/scala/2.13.0/scala-2.13.0.tgz
	tar -zxvf scala-2.13.0.tgz -C /data/soft
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
		export PATH=$PATH:$ZOOKEEKER_HOME/bin
		刷新环境变量使其生效： source /etc/profile
	4. 配置
		进入到Zookeeper安装目录： cd /usr/local/zookeeper
		拷贝一份conf目录下的配置文件，重命名为zoo.cfg: cp ./conf/zoo_sample.cfg ./conf/zoo.cfg

		编辑配置文件设置关键参数：
		tickTime=2000
		initLimit=5
		syncLimit=3
		dataDir=/data/zookeeper/data
		dataLogDir=/usr/local/zookeeper/logs
		clientPort=2181
		server.1=192.168.33.142:2888:3888
		server.2=192.168.33.140:2888:3888
		server.3=192.168.33.146:2888:3888
	5. 创建myid文件
		在创建参数dataDir对应的路径下新建myid文件，写入单独的一个数字，表示集群中该实例的编号，该值在集群中是唯一值，不可以重复，数字必须和zoo.cfg配置文件中的server.X中的X一一对应。
	6. 启动Zookeeper
		bin/zkServer.sh start
	7. 验证安装是否成功
		bin/zkServer.sh status（一个leader，两个follow）

* timeTime：Zookeeper中基础的参考时间，所有与时间相关的设置都为tickTime时间的整数倍，单位是毫秒。
* initLimit：Zookeeper Leader 与 Follower 初始连接时，Follower需要从Leader同步最新数据，该值表示Follower同步数据的最大超过时间，一般为整数，表示是tickTime的整数倍时间。
* syncLimit：Leader和Follower之间心跳检测的最大超时时间，超过这个时间则认为Follewer已经下线
* dataDir:Zookeeper持久化数据目录，建议与安装路径不在同一个路径下。
* dataLogDir:日志文件目录
* clientPort：监听客户端连接的端口号，默认值为2181.
* server.X=A:B:C。其中X是一个数字，表示这是第几号server；A是server所在的IP地址；B配置该server和集群中的leader交换消息所使用的端口；C配置选举leader时所使用的端口。

#### 1.2.2 Hadoop部署 ####

**1. Hadoop简介**

**2. Hadoop集群部署**

# 第二部分 核心技术 #

## 第2章 Spark详解 ##

### 2.1 Spark概述 ###

#### 2.1.1 Spark 概述 ####

**1. 核心概念介绍**

Client:客户都安进程，负责提交作业。
Driver:一个Spark作业有一个Spark Context，一个Spark Context对应一个Driver进程，作业的main函数运行在Driver中。Driver主要负责Spark作业的解析，以及通过DAGScheduler划分Stage，将Stage转化成TaskSet提交给TaskSeheduler任务调度器，进而调度Task到Executor上执行。
Executor
Catche
Application
Job
Task
Stage
TaskSet
RDD
DAG

**2. RDD介绍**