# 从Paxos到ZooKeeper分布式一致原理与实践 #

## 第1章 分布式架构 ##

### 1.1 从集中式到分布式 ###

#### 1.1.1 集中式的特点 ####

1.1.2 分布式的特点

分布式、对等式、并发性、缺乏全局时钟

1.1.3 分布式环境的各种问题

1.2 从ACID到CAP/BASE

1.2.1 ACID

## 第2章 一致性协议 ##

## 第5章 使用ZooKeeper ##

### 5.1 部署与运行 ###

#### 5.1.1 系统环境 ####

操作系统

Java环境

#### 5.1.2 集群与单机 ####

集群模式和单机模式

**集群模式**

**单机模式**

单机模式的部署步骤和集群模式的部署步骤基本一致，只要在zoo.cfg文件的配置上有些差异。

	zoo.cfg
	tickTime=2000
	dataDir=/var/lib/zookeeper/
	clientPort=2181
	initLimit=5
	syncLimit=2
	server.1=IP:2888:3888

**伪集群模式**

	zoo.cfg
	tickTime=2000
	dataDir=/var/lib/zookeeper/
	clientPort=2181
	initLimit=5
	syncLimit=2
	server.1=IP:2888:3888
	server.2=IP:2888:3889
	server.3=IP:2888:3890

#### 5.1.3 运行服务 ####

启动服务

停止服务

常见异常

### 5.2 客户端脚本 ###

	sh zkCli.sh -server ip:port

#### 5.2.1 创建 ####

使用create命令，可以创建一个ZooKeeper节点

	create [-s] [-e] path data acl
	其中，-s或-e分别指定节点特性：顺序或临时节点。默认情况下，即不添加-s或-e参数的，创建的是持久节点。

#### 5.2.2 读取 ####

读取相关的命令包括ls命令和set命令

**ls**

使用ls命令，可以列出ZooKeeper指定节点下的所有子节点。当然，这个命令只能看到指定节点下第一级的所有子节点。

	ls path [watch]
	path表示的是指定数据节点的节点路径

**get**

使用get命令，可以获取ZooKeeper指定节点的数据内容和属性信息。

	get path [watch]

#### 5.2.3 更新 ####

使用set命令，可以更新指定节点的数据内容。

	set path data [version]
	其中，data就是要更新的新内容。注意，set命令后面还有一个version参数，在ZooKeeper中，节点的数据是有版本概念的。

	set /zk-book 456
	执行完set，节点/zk-book的数据内容就已经被更新成“456”。在输出信息中，dataVersion的值由原来的0变成了1.

#### 5.2.4 删除 ####

使用delete命令，可以删除ZooKeeper上的指定节点。

	delete path [version]
	此命令中的version参数和set命令中的version参数的作用是一致的。

### 5.3 Java客户端API使用 ###

ZooKeeper作为一个分布式服务框架，主要用来解决分布式数据一致性问题，它提供了简单的分布式原语，并且对多种编程语言提供了API。

#### 5.3.1 创建会话 ####

客户端通过创建一个ZooKeeper（org.apache.zookeeper.ZooKeeper）实例来连接ZooKeeper服务器。

	4种构造方式：
	ZooKeeper(String connectString, int sessionTimeout, Watcher watcher);
	ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, boolean canBeReadOnly);
	ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, long sessionId, byte[] sessionPasswd);
	ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, long sessionId, byte[] sessionPasswd, boolean canBeReadOnly);
	使用任意一个构造方法都可以顺利完成与ZooKeeper服务器的会话（Session）创建

|参数名|说明|
|--|--|
|connectString| |
|sessionTimeout| |
|watcher| |
|canBeReadOnly| |
|sessionId和sessionPasswd| |

Zookeeper客户端和服务端会话的建立是一个异步的过程，在程序中，构造方法会在处理完客户端初始化工作后立即返回。

建立好一个可用的会话，在会话的生命周期中处于“CONNECTING”的状态。

构造放哪广发实现了与ZooKeeper服务器之间的TCP连接创建，负责维护客户端会话的生命周期。

构造方法来实例化了一个Zookeeper对象，从而建立了会话。类实现了Watcher接口，重写了process方法，该方法负责处理来自Zookeeper服务端的Watcher通知，在收到服务端发来的SyncConnected事件之后，解除主程序在CountDownLatch上等待阻塞。

复用sessionId和sessionPasswd的ZooKeeper对象实例



#### 5.3.2 创建节点 ####

客户端通过ZooKeeper的API来创建一个数据节点，有如下两个接口：

	String create(final String path,
				byte data[],
				List<ACL> acl,
				CreateMode createMode)
	void create(final String path, byte data[]
				List<ACL>acl, CreateMode createMode, StringCallback cb, Object ctx)

#### 5.3.4 读取数据 ####

