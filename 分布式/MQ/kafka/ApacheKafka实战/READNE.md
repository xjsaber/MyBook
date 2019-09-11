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

