# ZooKeeper分布式过程协同技术详解  #

## 第1章 简介 ##

### 1.1 Zookeeper的使命 ###

#### 1.1.1 Zookeeper改变了什么 ####

#### 1.1.2 Zookeeper不适用的场景 ####

不适合用作海量数据存储

#### 1.1.3 关于Apache项目 ####

#### 1.1.4 通过Zookeeper构建分布式系统 ####

消息延迟

处理器性能

时钟偏移

### 1.2 示例：主-从应用 ###

一个主从（master-worker）架构

主节点崩溃

从节点崩溃

通信故障

#### 1.2.1 主节点失效 ####

#### 1.2.2 从节点失效 ####

#### 1.2.3 通信故障 ####

## 第2章  了解ZooKeeper ##

### 2.1 Zookeeper基础 ###

创建（create）、获取（acquire）和释放（release）

#### 2.1.1 API概述 ####

/workers作为父节点,旗下每个znode子节点保存了系统中一个可用从节点信息。

/tasks节点作为父节点，其下每个znode子节点保存了所有已经创建并等待从节点执行的任务的信息。

/assign节点作为父节点，其下每个znode子节点保存了分配到某个从节点的一个任务信息，当主节点为某个从节点分配了一个任务

create /path data 创建一个名为/path的znode节点，并包含数据data。

delete /path 删除名为/path的znode。

exists /path 检查是否存在名/path的节点。

setData /path data 设置名为/path的znode的数据为data
 
getData /path 返回名为/path节点的数据信息

getChildren /path 返回所有/path节点的所有子节点列表

Zookeeper并不允许局部写入或读取znode节点的数据。当设置一个znode节点的数据或读取时，znode节点的内容会被整个替换或全部读取进来。

#### 2.1.2 znode的不同类型 ####



**持久节点和临时节点**

**有序节点**

#### 2.1.3 监视和通知 ####
ZooKeeper通常以远程服务的方式被访问。

#### 2.1.4 版本 ####

### 2.2 ZooKeeper架构 ###

独立模式（standalone）和仲裁模式（quorum）

#### 2.2.1 Zookeeper仲裁 ####

#### 2.2.2会话 ####

在对Zookeeper集合执行任何请求前，一个客户端必须先与服务建立会话。

### 2.3 开始使用ZooKeeper ###

#### 2.3.1 第一个Zookeeper会话 ####

#### 2.3.2 会话的状态和声明周期 ####

会话的生命周期（lifetime）是指会话从创建到结束的时期，无论会话正常关闭还是因超时而导致过期。

CONNECTING、CONNECTED、CLOSED和NOT_CONNECTED

#### 2.3.3 ZooKeeper与仲裁模式 ####

服务器可以使用多播来发现彼此，Zookeeper集合支持跨多个网络而不是单个网络。

	tickTime=2000
	initLimit=10
	syncLimit=5
	dataDir=./data
	clientPort=2181
	server.1=127.0.0.1:2222:2223
	server.2=127.0.0.1:3333:3334
	server.3=127.0.0.1:4444:4445

每一个server.n项指定了编号为n的ZooKeeper服务器使用的地址和端口号。每个server.n项通过冒号分隔为三部分，第一部分为服务器n的IP地址或主机名(hostname)，第二部分和第三部分为TCP端口号，分别用于仲裁通信和群首选举。

#### 2.3.4 实现一个原语：通过Zookeeper实现锁 ####
一个简单的例子就是通过锁来实现临界区域。

### 2.4 一个主-从模式例子的实现 ###

使用zkCli的目的仅仅是为了说明如何通过Zookeeper来实现协作菜谱。

主-从模式的模型中包括三个角色：

主节点
	主节点负责监视新的从节点和任务、分配任务给可用的从节点。

从节点
	从节点会通过系统注册自己，以确保主节点看到它们可以执行任务，然后开始监视新任务

客户端
	客户端创建新任务并等待系统的响应。

#### 2.4.1 主节点角色 ####

只有一个进程会成为主节点，所以一个进程成为ZooKeeper的主节点后必须锁定管理权。

#### 2.4.2 从节点、任务和分配 ####

#### 2.4.3 从节点角色 ####

#### 2.4.4 客户端角色 ####

客户端向系统中添加任务。

### 2.5小结 ###

## 第3章 开始使用ZooKeeper的API ##

### 3.1 设置ZooKeeper的CLASSPATH ###

	ZOOBINDIR="<path_to_distro>/bin"."$ZOOBINDIR"/zkEnv.sh

### 3.2 建立ZooKeeper会话 ###

ZooKeeper的API围绕Zookeeper的句柄（handle）而构建，每个API调用都需要传递每个句柄。这个句柄代表与Zookeeper之间的一个会话。

#### 3.2.1 实现一个Watcher ####

	public interface Watcher {
		void process(WatchedEvent event)
	}

#### 3.2.2 运行Watcher的示例 ####



### 3.3 获取管理权 ###

#### 3.3.1 异步获取管理权 ####

	void create(String path, byte[] data, List<ACL> acl,CreateMode createMode, AsyncCallback.StringCallback cb, Object ctx)

create方法的异步方法与同步方法非常相似，仅仅多了两个参数：

1. 提供回调方法的对象
2. 用户指定上下文信息（回调方法调用是传入的对象实例）

