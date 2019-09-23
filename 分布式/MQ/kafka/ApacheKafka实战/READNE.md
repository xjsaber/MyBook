# Apache Kafka实战 #

## 第1章 认识Apache Kafka ##

### 1.1 Kafka快速入门 ###

#### 1.1.1 下载并解压缩Kafka二进制代码压缩包文件 ####

#### 1.1.2 启动服务器 ####

#### 1.1.3 创建topic ####

#### 1.1.4 发送消息 ####

#### 1.1.5 消费消息 ####

### 1.2 消息引擎系统 ###

* 消息设计
* 传输协议设计

#### 1.2.1 消息设计 ####

消息通常采用结构化的方式进行设计：比如SOAP协议中的消息就采用了XML格式，而WebService也支持JSON格式的消息。（Kafka采用二进制来保存）

消息主体本身一般都是结构化的数据。

#### 1.2.2 传输协议设计 ####

从狭义的角度来说，协议传输协议指定了消息在不同系统之间传输的方式，目前主流的协议包括AMQP、Web Service+SOAP以及微软的MSMQ等。从广义的角度来说，这类协议可能包括任何能够在不同系统间传输消息或是执行语义操作的协议或框架。

#### 1.2.3 消息引擎范性 ####

传输协议作为一个基础构建块（building block），其服务于消息引擎系统实现的消息引擎范型，描述了消息引擎系统的两个不同的子部分是如何互连且交互的。如果把消息引擎系统的这两个子系统比喻成两座城市，那么之前谈到的传输协议就是需要铺设的沥青公路，而引擎范型决定了来往穿梭于这两座城市的路线。

最常见的两种消息引擎范型是消息队列模型和发布/订阅模型。

1. 消息队列
2. 发布/订阅模型

#### 1.2.4 Java消息服务 ####

Java消息服务，即Java Message Service（简称JMS）

### 1.3 Kafka概要设计 ###

* 吞吐量/延时
* 消息持久化
* 负载均衡和故障转移
* 伸缩性

#### 1.3.1 吞吐量/延时 ####

高吞吐量、低延时：

1. Kafka的写入操作很快，得益于它对磁盘的使用方法的不同。虽然Kafka会持久化所有数据的磁盘，但本质上每次写入操作其实都只是把数据写入到操作系统的页缓存（page cache）中，然后由操作系统自行决定什么时候把页缓存中的数据写回磁盘上。

* 操作系统页缓存在内存中分配，所以消息写入的速度非常快。
* Kafka不必直接与底层的文件系统打交道。所有繁琐的I/O操作都交由操作系统来处理。
* Kafka写入操作采用追加写入（append）的方式，避免了磁盘随机写操作。

普通SAS磁盘顺序读/写非常快，所以在设计时采用了追加写入消息的方式。

* 大量使用操作系统页缓存，内存操作速度快且命中率高。
* Kafka不直接参与物理I/O操作，而是交由最擅长此事的操作系统来完成
* 采用追加写入方式，摈弃了缓慢的磁盘随机读/写操作。
* 使用以sendfile为代表的零拷贝技术加强网络间的数据传输效率

#### 1.3.2 消息持久化 ####



#### 1.3.3 负载均衡和故障转移 ####

负载均衡（load balancing）和故障转移（fail-over）功能

智能化的分区领导者选举（partition leader election）

Kafka服务器支持故障转移的方式就是使用会话机制。每台Kafka服务器启动后以会话的形式把自己注册到ZooKeeper服务器上。

#### 1.3.4 吞吐量/延时 ####

### 1.4 Kafka基本概念与术语 ###

* 生产者发送消息给Kafka服务器
* 消费者从Kafka服务器读取消息
* Kafka服务器依托ZooKeeper集群进行服务的协调管理

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

构造一个producer

1. 构造一个`java.util.Properties`，至少指定bootstrap.servers、key.serializer和value.serializer这3个属性。
2. 使用上一步中创建的Properties实例构造KafkaProducer对象
3. 构造代发送的消息对象ProducerRecord，指定消息要发送到topic、分区以及对应的key和value。PS.分区和key信息可以不用指定，由Kafka自行确定目标分区。
4. 调用KafkaProducer的send方法送消息。
5. 关闭KafkaProducer。

**1. 构造Properties对象**

[http://www.ishenping.com/ArtInfo/1426033.html](http://www.ishenping.com/ArtInfo/1426033.html)

*bootstrap.servers*

指定一组host:port,用于创建向Kafka broker服务器的连接，比如k1:9092，k2:9092, k3:9092

producer使用时需要替换成实际的broker列表。（如果Kafka集群中机器数很多，那么只需要指定部分broker即可。不管指定几台机器，producer都会通过该参数找到并发现集群中所有的broker。为该参数指定多态机器只是为了故障转移使用。）

如果broker端没有显式配置listeners使用IP地址，最好将该参数配置成主机名，而不是IP地址（FQDN）

*key.serializer*

被发送到broker端的任何消息的格式都必须是字节数组。各个组件首先做序列化，然后才能发送到broker。

org.apache.kafka.common.serialization.StringSerializer，该类会一个字符串类型转换成字节数组。

*value.serializer*

与key.serializer类似，只是被用来对消息体（即消息value）部分序列化，将消息value部分转化成字节数组。

PS，两个参数必须是全限定类名称。

**2. 构造KafkaProducer对象**

KafkaProducer是producer的主入口

	Producer<String, String> producer = new KafkaProducer<>(props);

创建producer时也可以同时指定key和value的序列化

**3. 构造ProducerRecord对象**

构造KafkaProducer实例后，下一步是构造消息实例。

	new ProductRecord<>("my-topic", Integer.toString(i))

最好让Kafka自行决定实时间戳（时间戳索引文件的索引项都是严格按照时间戳顺序排列的）

**4. 发送消息**

同步实现和异步发送+回调

**异步发送**

	producer.send(record, new Callback() {
	    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
	        if (e == null) {
	            // 消息发送成功
	        } else {
	            // 执行错误处理逻辑
	        }
	    }
	});

recordMetadata和e不会同时非空，当消息发送成功时，exception是null；反之metadata就是null。

**同步发送**

Future.get()无限等待结果返回。如果没有错误，get将返回对应的RecordMetadata实例（包含了已发送消息的所有元数据信息），包括消息发送的topic、分区以及该消息在对应分区的位移信息。

	ProducerRecord<String, String>

* LeaderNotAvailableException:分区的leader副本不可用，这通常出现在leader换届选举期间，因此通常是瞬时的异常，重试之后可以自行恢复
* NotControllerException:controller当前不可用
* NetworkException:网络瞬时故障导致的异常

所有可重试异常都继承自org.apache.kafka.common.errors.RetriableException抽象类。

* RecordTooLargeException：发送的消息尺寸过大，超过了规定的大小上限，无法恢复。
* SerializationException：序列化失败异常，无法恢复。
* kafkaException：其他类型的异常。

**5. 关闭producer**

* 无参数close producer会被允许先处理完之前的发送请求后再关闭。
* 提供一个带超时参数的close方法close（timeout），若timeout超时，则producer会强制结束。

谨慎使用带超时的close方法

#### 4.2.2 producer主要参数 ####

**acks**

**buffer.memory**

**compression.type**

**retries**

**batch.size**

**linger.ms**

**max.request.size**

**request.timeout.ms**

### 4.3 消息分区机制 ###

#### 4.3.1 分区策略 ####

默认partitioner会尽力确保具有相同key的所有消息都会被发送到相同的分区上；若没有为消息指定key，则该partitioner会选择轮询的方式来确保消息在topic的所有分区上均匀分配。

#### 4.3.2 自定义分区机制 ####

对于有key的消息而言，Java版本producer自带的partitioner会根据murmur2算法计算消息key 的哈希值，然后对总分区数求模得到消息要被发送的目标分区号。

1. 在producer程序中创建一个类，实现org.apache.kafka.clients.producer.Partitioner接口
2. 在用于构造KafkaProducer的Proper对象中设置partitioner.class参数

partitioner接口的主要方法是partition方法，该方法接收消息所属的topic、key和value，还有集群的元数据信息



### 4.4 消息序列化 ###

#### 4.4.1 默认序列化 ####

在网络中发送数据都是以字节的方式。Apache Kafka支持用户给broker发送各种的消息。

* ByteArraySerializer
* ByteBufferSerializer
* ByteSerializer
* DoubleSerializer
* IntegerSerializer
* LongSerializer
* StringSerializer

#### 4.4.2 自定义序列化 ####

自定义的serializer

1. 定义数据对象格式
2. 创建自定义序列类
3. 在用于KafkaProducer的Properties对象中设置key.serializer或value.serializer

### 4.5 producer拦截器 ###

org.apache.kafka.clients.producer.ProducerInterceptor

* onSend（ProducerRecord）：producer确保在消息被序列化以计算分区前调用该方法。用户可以在该方法中对消息做任何操作，但最好保证不要修改消息所属的topic和分区，否则会影响分区的计算（该方法封装进KafkaProducer.send方法中，即它运行在用户主线程中）。
* onAcknowledgement（RecordMetadata，Exception）：该方法在消息被应答之前或消息发送失败时调用，并且通常都是在producer回调逻辑触发之前。onAcknowledgement运行在producer的I/O线程中（不要在该方法中放入很“重”的逻辑，否则会拖慢producer的消息发送效率）
* close：关闭interceptor，主要用于执行一些资源清理工作

interceptor可能运行在多个线程中中，因此在具体实现时用户需要自行确保线程安全。（另外，若指定了多个interceptor，则producer将按照指定顺序调用它们，同时把每个interceptor中捕获的异常记录到错误日志中而不是向上传递）

### 4.6 无消息丢失配置 ###

KafkaProducer.send方法仅仅把消息放入缓冲区，由一个专属I/O线程负责从缓冲区提取消息并封装进消息batch中，然后发送出去。

producer存在数据丢失的窗口：

* 若I/O线程发送之前producer崩溃，则存储缓冲区中的消息全部丢失了。
* 消息的乱序（由于某些原因导致record1未发送成功，同时Kafka又配置了重试机制以及max.in.flight.requests.per.connection大于1，那么record1在日志的位置反而位于record2之后，这样就造成了消息的乱序）

解决问题：

1. 既然异步发送可能丢失数据，改成同步


* block.on.buffer.full=true
* acks=all or-1
* netries=Integer.MAX_VALUE
* 使用带回调机制的send发送消息，即KafkaProducer.send(record, callback)
* Callback逻辑中
* replication.factor=3
* min.insync.replcas=2
* relication.factor>min.insync.replicas
* enable.auto.commit=false

#### 4.6.1 producer端配置 ####

**block.on.buffer.full=true**

**acks=all**

等到所有follower都响应了发送消息才能认为提交成功（producer端最强的程度的持久化保证）

**retries=Integer.MAX.VALUE**

**max.in.flight.requests.per.connection=1**

**使用带有回调机制的send**

实际环境中一定要使用带有回调机制的send版本，即KafkaProducer.send(record, callback)

**Callback逻辑中显式立即关闭producer**



#### 4.6.2 broker端配置 ####

unclean.leader.election.enable=false

**replication.factor>=3**

设置成3参考了Hadoop及业界通道通用的三备份原则，强调的是一定要使用多个副本来保存分区的消息。

**min.insync.replicas>1**

用于控制某条至少被写入到ISR中的多少个副本才算成功，设置成大于1是为了提升producer	端发送语义的持久性。记住只有在producer端acks被设置成all或-1时，这个参数才有意义。在实际使用时，不要使用默认值。

**确保replication.factor>min.insync.replicas**

若两者相等，那么只要一个副本挂掉，分区就无法正常工作，虽然有很高的持久性但可用性被极大地降低了。推荐配置成replication.factor=min.insyn.replcas+1。

### 4.7 消息压缩 ###

### 4.8 多线程处理 ###

**多线程单KafkaProducer实例**

全局构造一个KafkaProducer实例，然后在多个线程中共享使用。由于KafkaProducer是线程安全，所以这种使用方式也是线程安全的。

**多线程多KafkaProducer实例**

每个producer主线程都构造一个KafkaProducer实例，并且保证此实例在该线程中封闭（thread confinement，线程封闭是实现线程安全的重要手段之一）。

### 4.9 旧版本producer ###

### 4.10 本章小结 ###

## 第5章 consumer开发 ##

### 5.1 consumer概览 ###

#### 5.1.1 消费者（consumer） ####

#### 5.1.2 消费者组（consumer group） ####

消费者使用一个消费者组名（即group.id）来标记自己，topic的每条消息都只会被发送到每个订阅它的消费者组的一个消费者实例上。

* consumer group
* group.id
* 对某个group而言，订阅topic的每个分区只能分配给该group下的一个consumer实例（当然该分区还可以被分配给其他订阅该topic的消费者组）

#### 5.1.3 位移（offset） ####

offset指代的是consumer端的offset。

#### 5.1.4 位移提交 ####

#### 5.1.5 _consumer_offsets ####

#### 5.1.6 消费者组重平衡 ####

### 5.2 构建consumer ###

#### 5.2.1 consumer程序实例 ####

构造一个consumer group从指定Kafka topic消费消息。

1. 构造一个java.util.Properties对象至少自定bootstrap.servers、key.deserializer、value.deserializer和group.id的值
2. 使用上一部创建的Properties实力构造KafkaConsumer对象。
3. 调用KafkaConsumer.subscribe方法订阅consumer group感兴趣的topic列表。
4. 循环调用KafkaConsumer.poll方法获取装在ConsumerRecord的topic消息。
5. 处理获取的ConsumerRecord对象
6. 关闭KafkaConsumer。

**1. 构造Properties**

bootstrap.servers

key.deserializer

value.deserializer

**2. 构造KafkaConsumer对象**

设置好上述4个参数后，构造KafkaConsumer对象。

**3. 订阅topic列表**



**4. 获取消息**



**5. 处理ConsumerRecord对象**



**6. 关闭consumer**

显式关闭consumer以及释放KafkaConsumer运行过程中占用的各种系统资源（比如线程资源、内存、Socket连接等）。

关闭方法有两种，如下：

* KafkaConsumer.close()：关闭consumer并最多等30秒
* KafkaConsumer.close(timeout)

#### 5.2.2 consumer脚本命令 ####

## 第6章 Kafka设计原理 ##

### 6.1 broker端设计架构 ###

* 消息设计
* 集群管理
* 副本与ISR机制
* 日志存储
* 请求处理协议
* controller设计
* broker状态机
* broker通信原理

#### 6.1.1 消息设计 ####

**1. 消息格式**

如果由Java创建，一条普通的Kafka消息，即使未初始化key和value，也需要占用40字节的内存空间，而其中7字节更是完全被浪费掉的。

Kafka的实现方式本质上是使用Java NIO的ByteBuffer来保存下线哦，同时依赖文件系统提供的页缓存机制，而非依靠Java的堆缓存。

**2. 版本变迁**

V0、V1、V2

*V0：主要指Kafka 0.10.0.0之前的版本*

* CRC校验码：4字节的CRC校验码，用于确保消息在传输过程中不会被恶意篡改
* magic：单字节的版本号。V0版本magic=0，V1版本magic=1,V2版本magic=2
* attribute：单字节属性字段，目前只使用低3位表示消息的压缩类型
* key长度字段：4字节的消息key长度消息。若未指定key，则给该字段赋值为-1。
* key值：消息key，长度由上面的“key长度字段”值指定。如果“key长度字段”值是-1，则无key，消息没有该字段。
* value长度字段：4字节的消息长度。若未指定value，则给该字段赋值-1
* value值：消息value，长度由上面的“value长度字段”值指定。如果value长度字段“”值是-1，则无value，消息没有该字段。

缺点：

1. 没有消息的时间信息，Kafka定期删除过期日志只能依靠日志段文件的“最近修改时间”，但这个时间极易受到外部操作的干扰。（若不小心堆日志段文件执行了UNIX的touch命令，该日志文件的最近修改时间就被更新了。一旦这个时间被“破坏”或者更改，Kafka将无法堆哪些消息过期作出正确判断）
2. 很多流式处理框架都需要消息保存时间信息以便对消息执行时间窗口等聚合操作

*V1*

差别：

1. 引入了8字节的时间戳字段，而其他字段的含义与V0版本相同。
2. attribute字段的使用

*V2*

#### 6.1.2 集群管理 ####

**1. 成员管理**

每当一个broker启动时，会将自己注册到ZooKeeper下的一个节点。

* listener_security_protocol_map
* endpoints
* rack
* jmx_port
* host
* port
* timestamp
* version

**2. ZooKeeper路径**

#### 6.1.3 副本与ISR设计 ####

#### 6.1.5 日志存储设计 ####

**1. Kafka日志**

Kafka日志的设计更像是关系型数据库中的记录，抑或是某些系统中所谓的提交日志（commit log）或日志（journal）

**2. 底层文件系统**

**3. 索引文件**

## 第7章 管理Kafka集群 ##

* 集群与topic管理
* 消费者管理
* 常用脚本工具
* 集群管理API
* MirrorMaker的使用
* Kafka安全
* 常见问题

### 7.1 集群管理 ###

#### 7.1.1 启动broker ####

key点：

1. -deamon参数启动服务器（生产环境强烈要求）

#### 7.1.2 关闭broker ####

	-daemon

	bin/kafka-server.start.sh -daemon <path>/server.properties

	bin/kafka-server-start.sh <path>/server.properties & 通过添加&符号使该命令在后台运行，但当用户从会话登出（log out）时该进程会被自动kill掉，从而导致broker关闭。


**1. 前台方式启动broker进程时**

