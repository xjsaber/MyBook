# Kafka核心技术与实战 #

开篇词

## 开篇词 | 为什么要学习Kafka ##

![8b28137150c70d66200f649e26ff2395.jpg](img/8b28137150c70d66200f649e26ff2395.jpg)

kafka入门

## 01 | 消息引擎系统ABC ##

Apache Kafka是一款开源的消息引擎系统。

* 点对点模型：也叫消息队列模型。
* 发布/订阅模型：有个主题（Topic）的概念，理结成逻辑语义相近的消息容器。该模型也有发送方（发布者）和接收方（订阅者）。可能存在多个发布者相同的主题发送消息，而订阅者也可能存在多个。

系统A不能直接发送消息给消息B，中间还要隔一个消息引擎，是为了“削峰填谷”。

## 02 | 一篇文章带你快速搞定Kafka术语 ##

在Kafka中，发布订阅的对象是主题（Topic）。

向主题发布消息的客户端应用程序称为生产者（Producer），生产者程序通常持续不断地向一个或多个主题发送消息，而订阅这些主题消息的客户端应用程序就被称为消费者。和生产者类似，消费者也能够订阅多个主题的消息。我们把生成者和消费者统称为客户端（Clients）。

### Kafka的高可用机制 ###

1. Kafka 的服务器端由被称为 Broker 的服务进程构成，即一个 Kafka 集群由多个 Broker 组成，Broker 负责接收和处理客户端发送过来的请求，以及对消息进行持久化。多个Broker放置在多个机器上，是Kafka提供高可用的手段之一。
2. 备份机制（Replication）。备份的思想很简单，就是把相同的数据拷贝到多台机器上，而这些相同的数据拷贝在 Kafka 中被称为副本（Replica）。。Kafka 定义了两类副本：
	1. 领导者副本（Leader Replica）和追随者副本（Follower Replica）。
	2. 前者对外提供服务，这里的对外指的是与客户端程序进行交互；
	3. 而后者只是被动地追随领导者副本而已，不能与外界进行交互。
	
Kafka 中的分区机制指的是将每个主题划分成多个分区（Partition），每个分区是一组有序的消息日志。

Kafka 的三层消息架构：

1. 第一层是主题层，每个主题可以配置 M 个分区，而每个分区又可以配置 N 个副本。
2. 第二层是分区层，每个分区的 N 个副本中只能有一个充当领导者角色，对外提供服务；其他 N-1 个副本是追随者副本，只是提供数据冗余之用。
3. 第三层是消息层，分区中包含若干条消息，每条消息的位移从 0 开始，依次递增。
4. 最后，客户端程序只能与分区的领导者副本进行交互。

### Kafka Broker 是如何持久化数据的 ###

Kafka 使用消息日志（Log）来保存数据，一个日志就是磁盘上一个只能追加写（Append-only）消息的物理文件。因为只能追加写入，故避免了缓慢的随机 I/O 操作，改为性能较好的顺序 I/O 写操作，这也是实现 Kafka 高吞吐量特性的一个重要手段。

**清理过期日志**

通过日志段（Log Segment）机制。在 Kafka 底层，一个日志又近一步细分成多个日志段，消息被追加写到当前最新的日志段中，当写满了一个日志段后，Kafka 会自动切分出一个新的日志段，并将老的日志段封存起来。Kafka 在后台还有定时任务会定期地检查老的日志段是否能够被删除，从而实现回收磁盘空间的目的。



### 小结 ###

消息：Record。kafka是消息引擎，这里的消息就是指Kafka处理的主要对象。
主题：Topic。主题是承载消息的逻辑容器。在实际使用终多用来区分具体的业务。
分区：Partition。一个有序不变的消息序列。每个主题中可以有多个分区。
消息位移：Offset。表示分区中同一条消息能够被拷贝到多个地方以提供数据冗余，这些地方就是所谓的副本。副本
副本：Replica。Kafka中同一条消息能够被拷贝到多个地方以提供数据冗余，这些地方都是所谓的副本。副本还分为领导者副本和追随者副本，各自有不同的角色划分。副本是在分区层级下的，即每个分区可配置多个副本实现高可用。
生产者：Producer。向主题发布新消息的应用程序。
消费者：Consumer。从主题订阅新消息的应用程序。
消费者位移：Consumer Offset。表征消费者消费进度，每个消费者都有自己的消费者位移。
消费者组：Consumer Group。多个消费者实例组成的一个组，同时消费多个分区以实现高吞吐。
重平衡：Rebalance。消费者组内某个消费者实例挂掉之后

## 03 | Kafka只是消息引擎系统吗？ ##

**Apache Kafka 是消息引擎系统，也是一个分布式流处理平台（Distributed Streaming Platform）**



## 04 | 我应该选择哪种Kafka？ ##

### 你知道几种Kafka？ ###

#### 1. Apache Kafka ####

#### 2. Conflunet Kafka ####

#### 3. Cloudera/Hortonworks Kafka ####

## 05 | 聊聊Kafka的版本号 ##

### Kafka版本命名 ###

### Kafka版本演进 ###

### 小结 ###

Kafka的版本号

* 0.7版本：只提供了最基础的消息队列功能。
* 0.8版本：引入了副本机制，至此Kafka成为了一个真正意义上完备的分布式高可靠消息队列解决方案
* 0.9.0.0版本：在呢国家了基础的安全认证/权限功能；使用Java重写了新版本消费者API；引入了Kafka Connect组件。
* 0.10.0.0版本：引入了KafkaStreams，正式升级成分布式流处理平台。
* 0.11.0.0版本：提供了幂等性ProducterAPI以及事务API；对Kafka消息格式做了重构。
* 1.0和2.0版本：主要还是KafkaStream的各种改进。

## 06 | Kafka线上集群部署方案 ##

### 操作系统 ###

Kafka由Scala语言和Java语言编写而成，编译之后的源代码就是普通的".class"文件。

* I/O模型的使用
* 数据网络传输效率
* 社区支持度

主流的I/O模型通常有5种类型：阻塞式I/O、非阻塞式I/O、I/O多路复用、信用驱动I/O和异步I/O。每个I/O模型都有各自典型的使用场景，比如Java中Socket对象的阻塞模式和非阻塞模式就对应前两种模型；而Linux中系统调用select函数就属于I/O多路复用模型；epoll系统调用则介于第三种和第四种模型之间；第五种模型，Linux系统支持，但Windows系统提供了一个叫IOCP线程模型属于这一种。

Kafka使用Java的selector，selector在Linux上的实现机制是epoll，而Windows平台上实现的机制是select。

Kafka生产和消费的消息都是通过网络传输的，而消息保存在磁盘。故kafka需要在磁盘和网络间进行大量数据传输。

零拷贝（Zero Copy）技术，就是当数据在磁盘和网络进行传输时

### 磁盘 ###

### 磁盘容量 ###

Kafka需要将消息保存在底层的磁盘上，

### 带宽 ###

### 小结 ###

|因素|考量点|建议|
|--|--|--|
|操作系统|操作系统I/O模型|将kafka部署在Linux系统上|
|磁盘|磁盘I/O模型|普通环境使用机械硬盘，不需要搭载RAID|
|磁盘容量|根据消息数、留存时间预估磁盘容量|实际使用中建议预留20%~30%的磁盘空间|
|带宽|根据实际带宽资源和业务SLA预估服务器数量|对于千兆网络，建议每台服务器按照700Mbps来计算，避免大流量下的丢包|

## 07、08 | 最最最重要的集群参数配置 ##

### Broker端参数 ###

log.dirs：在线上生产环境配置多个路径，

* 提升读写性能
* 能够实现故障转移



### 小结 ###

### Topic级别参数 ###

### JVM参数 ###

### 操作系统参数 ###

### 小结 ###

客户端实践及原理剖析

## 09 | 生产者消息分区机制原理剖析 ##

## 10 | 生产者压缩算法面面观 ##

## 11 | 无消息丢失配置怎么实现？ ##

#### Broker端参数 ####

* 与存储信息相关的参数
* 与Zookeeper相关的参数
* 与Broker连接相关的参数
* 关于Topic管理的参数
* 关于数据留存的参数：`log.retention.{hour|minutes|ms}`、`log.retention.bytes`和`message.max.bytes`

#### Topic级别参数 ####

* retention.ms
* max.message.bytes：决定了Kafka Broker能够正常接收该Topic的最大消息大小。

#### JVM ####

* KAFKA_HEAP_OPTS：指定堆大小
* KAFKA_JVM_PERFORMANCE_OPTS：指定GC参数

#### 操作系统参数 ####

* 文件描述符限制
* 文件系统类型
* Swapping
* 提交时间

## 13 | Java生产者是如何管理TCP连接的？ ##

*Kafka 的 Java 生产者是如何管理 TCP 连接的。*

### 为何采用 TCP？ ###

Apache Kafka 的所有通信都是基于 TCP 的，而不是基于 HTTP 或其他协议。

**TCP的特性：**

* 多路复用请求，即 multiplexing request，是指将两个或多个数据流合并到底层单一物理连接中的过程。TCP 的多路复用请求会在一条物理连接上创建若干个虚拟连接，每个虚拟连接负责流转各自对应的数据流。其实严格来说，TCP 并不能多路复用，它只是提供可靠的消息交付语义保证，比如自动重传丢失的报文。
* 作为一个基于报文的协议，TCP 能够被用于多路复用连接场景的前提是，上层的应用协议（比如 HTTP）允许发送多条消息。

HTTP库在很多编程语言中略显简陋

### Kafka 生产者程序概览 ###

Kafka 的 Java 生产者 API 主要的对象就是 KafkaProducer。

1. 构造生产者对象所需的参数对象。
2. 利用第 1 步的参数对象，创建 KafkaProducer 对象实例。
3. 使用 KafkaProducer 的 send 方法发送消息。
4. 调用 KafkaProducer 的 close 方法关闭生产者并释放各种系统资源。

当我们开发一个 Producer 应用时，生产者会向 Kafka 集群中指定的主题（Topic）发送消息，这必然涉及与 Kafka Broker 创建 TCP 连接。*那么，Kafka 的 Producer 客户端是如何管理这些 TCP 连接的呢？*

### 何时创建 TCP 连接？ ###

生产者代码是什么时候创建 TCP 连接的

* Producer producer = new KafkaProducer(props)
* producer.send(msg, callback)。

*在创建 KafkaProducer 实例时，生产者应用会在后台创建并启动一个名为 Sender 的线程，该 Sender 线程开始运行时首先会创建与 Broker 的连接*。

## 23 | Kafka副本机制详解 ##

1. 提供数据冗余
2. 提供高伸缩性
3. 改善数据局部性

#### 副本定义 ####

#### 副本角色 ####

基于领导者（Leader-based）的副本机制。

1. 在Kafak中，副本分成两类：领导者副本（Leader Replica）和追随者副本（Follower Replica）

#### In-sync Replicas(ISR) ####

### 小结 ###

Replication

## 21 | Java 消费者是如何管理TCP连接的? ##



## 24 | 请求是怎么被处理 ##

特别放送

## 加餐 | 搭建开发环境、阅读源码方法、经典学习资料大揭秘 ##

1. 如何搭建 Kafka 开发环境？
2. 如何阅读 Kafka 源码？
3. Kafka 的学习资料。

### Kafka开发环境搭建 ###

#### 第1步：安装Java和Gradle ####

安装好 Java 和 Gradle，同时在 IDEA 中安装 Scala 插件。你最好把 Java 和 Gradle 环境加入到环境变量中。

#### 第2步：下载Kafka的源码 ####

下载Kafka的源码

	$ cd Projects
	$ git clone https://github.com/apache/kafka.git

#### 第 3 步：下载 Gradle 的 Wrapper 程序套件 ####

代码下载完成之后，会自动创建一个名为 kafka 的子目录，此时需要进入到该目录下，执行下面的这条命令，主要目的是下载 Gradle 的 Wrapper 程序套件。


	$ gradle
	Starting a Gradle Daemon (subsequent builds will be faster)
	
	> Configure project :
	Building project 'core' with Scala version 2.12.9
	Building project 'streams-scala' with Scala version 2.12.9
	
	Deprecated Gradle features were used in this build, making it incompatible with Gradle 6.0.
	Use '--warning-mode all' to show the individual deprecation warnings.
	See https://docs.gradle.org/5.3/userguide/command_line_interface.html#sec:command_line_warning

#### 第 4 步：将 Kafka 源码编译打包成 Jar 文件 ####

	./gradlew clean releaseTarGz

#### 第 5 步：把 Kafka 源码工程导入到 IDEA 中 ####

	$ ./gradlew idea  #如果你用的是Eclipse，执行./gradlew eclipse即可

## Kafka 源码阅读方法 ###

## 用户故事 | 黄云；行百里者半九十 ##

结束语

## 结束语 | 以梦为马，莫负韶华！ ##

10000 个小时的锤炼，是所有人从平凡人变成世界级大师的必要条件。

*1. 首先，最重要的就是夯实技术基本功。这是我们 IT 从业者赖以生存的基石。*

基本功：比如*操作系统、数据结构*等，*对 Java 语言的掌握*。

熟练掌握甚至精通 Java，是学好大数据框架的基石！所谓精通，*不仅仅是要求你熟练使用 Java 进行代码开发*，更要求你对 JVM 底层有详细的了解。

1. *持续精进自己的Java功底*，可以去 Java 官网上，把 Java 语言规范和 JVM 规范熟读一遍
2. *提升自己的Java多线程开发以及I/O开发能力*，Kafka 可是大量使用 NIO 实现网络通信的
3. *掌握JVM调优和GC*，尝试去读一读《Java Performance》

*理论*

分布式系统领域内的诸多经典问题，是设计并开发任何一款分布式系统都要面临和解决的问题，比如我们耳熟能详的一致性问题、领导者选举问题、分区备份问题等。这些问题在 Kafka 中都有体现，我们在专栏里面也有所涉及。因此，*分布式系统的诸多基础性概念，是帮助你日后深入掌握大数据分布式框架的重要因素*。

强调完理论，自然就要引出*实践*了。

* *真正的实践一定要包含你自己的思考和验证，而且要与真实业务相绑定*。在实际工作中进行学习，往往是学得最快、掌握得也最扎实的学习方式。
* 在实际工作工程中，记录下遇到问题、解决问题的点点滴滴，并不断积累。
* 不重复犯错


