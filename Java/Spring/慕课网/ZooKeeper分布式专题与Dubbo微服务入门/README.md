ZooKeeper分布式专题与Dubbo微服务入门

# 第1章 分布式系统概念与ZooKeeper简介 #

## 1-2 什么是分布式 ##

根据业务进行分拆

## 1-3 分布式系统的瓶颈以及zk的相关特性 ##

分布式锁-同步

* 一致性：数据一致性，数据按照顺序进入数据库
* 原子性：事务要么成功要么失败，不会局部化
* 单一视图：客户端连接在集群中的任一zk节点，数据都是一致的
* 可靠性：每次对zk的操作状态都会保存在服务端
* 实时性：客户可以读取到zk服务端的最新数据

# 第2章 ZooKeeper安装 #

## 2-1 JDK的安装 ##

## 2-2 zookeeper下载、安装以及配置环境变量 ##

### 单机zookeeper安装 ###

* zookeeper下载、安装以及配置环境变量
* zookeeper文件夹主要目录介绍
* zookeeper配置文件介绍，运行zk

	vim /etc/profile
	ZOOKEEPER_HOME = /usr/local/zookeeper

## 2-3 zookeeper文件夹主要目录介绍 ##

* bin：主要一些运行的命令
* conf：存放配置文件，其中我们需要修改zoo_sample_cfg
* contrib：附加的功能
* dist-maven：mvn编译后的目录
* docs：文档
* lib：需要依赖的jar包
* recipes：案例demo代码
* src：源码

## 2-4 zookeeper配置文件介绍，运行zk ##

zoo.cfg配置

* tickTime：用于计算的时间单元。比如session超时：N* tickTime
* initLimit：用于集群，允许从节点连接并同步到master节点的初始化连接时间，以tickTime的倍数来表示
* syncLimit：用于集群，master主节点与从节点之间发送消息，请求和应答时间长度（心跳机制）
* dataDir：必须配置
* dataLogDir：日志目录，如果不配置会和dataDir公用
* clientPort：连接服务器的端口，默认2181

## zookeeper配置文件介绍，运行zk ##

