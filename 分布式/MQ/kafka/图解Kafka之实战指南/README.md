# 图解Kafka之实战指南 #

## 初识Kafka ##

消息系统：

存储系统

流式处理平台

### 基础概念 ###

offset是消息在分区中的唯一标识，Kafka通过它来保证消息在分区的顺序性，不过offset并不跨越分区，也就是说，Kafka保证的是分区有序而不是主题有序。

## Kafka入门 ##

### 安装与配置 ###

jps命令只是用来确认Kafka服务的进程已经正常启动。是否能够正确对外提供服务，还需要通过发送和消费消息进行验证。

### 生产与消费 ###


### 服务端参数配置 ###

## 3 生产者客户端开发 ##

#### 客户端开发 ####

1. 配置生产者客户端参数及创建相应的生产者实例。
2. 构建待发送的消息。
3. 发送消息
4. 关闭生产者实例。

#### 必要的参数配置 ####

* bootstrap.servers
* key.serializer和value.serializer

KafkaProducer是线程安全的，可以在多个线程中共享单个KafkaProducer实例，也可以将KafkaProducer实例进行池化来供其他线程调用。

### 消息的发送 ###

	public ProducerRecord(String topic, Integer partition, Long timestamp, 
	                      K key, V value, Iterable<Header> headers)
	public ProducerRecord(String topic, Integer partition, Long timestamp,
	                      K key, V value)
	public ProducerRecord(String topic, Integer partition, K key, V value, 
	                      Iterable<Header> headers)
	public ProducerRecord(String topic, Integer partition, K key, V value)
	public ProducerRecord(String topic, K key, V value)
	public ProducerRecord(String topic, V value)

针对不同的消息，需要构建不同的ProducerRecord对象，在实际应用中创建ProducerRecord对象是一个非常频繁的动作。

send方法本身就是异步的，但在执行send()后直接链式调用get方法来阻塞等待Kafka的响应。

RecordMetadata对象，在RecordMetadata对象里包含了消息的一些元数据信息，比如当前消息的主题、分区号、分区中的偏移量（offset）、时间戳等。

Future表示一个任务的生命周期，并提供了相应的方法来判断任务是否已经完成或取消，以及获取任务的结果和取消任务等。

KafkaProducer中一般会发生两种类型的异常：可重试的异常和不可重试的异常。常见的可重试异常有：NetworkException、LeaderNoteAvaiableException、UnknownTopicOrParitionException、NotEnoughReplicasException、NotCoordinatorException。

	producer.send(record1, callback1);
	producer.send(record2, callback2);

对于同一个分区而言，如果消息record1于record2之前先发送，那么KafkaProducer就可以保证对应的callback1在callback2之前调用。也就是说，回调函数的调用可以保证分区有序。

## 4 序列化-分区器-拦截器 ##

### 序列化 ###

org.apache.kafka.common.serialization.Serializer 接口，此接口有3个方法：

	public void configure(Map<String, ?> configs, boolean isKey)
	public byte[] serialize(String topic, T data)
	public void close()

close()一般是一个空方法，如果实现了此方法，则必须确保方法的幂等性，因为这个方法很可能会被Kafkaproducer调用多次。

**StringSerializer** 

1. configure()方法，这个方法是在KafkaProducer实例的时候调用的，主要用来确定编码类型，不过一般客户端对于key.serializer.encoding、value.serializer.encoding和serializer.encoding这几个参数都不会配置。

### 分区器 ###

### 生产者拦截器 ###

两种拦截器：生产者拦截器和消费者拦截器

org.apache.kafka.clients.producer. ProducerInterceptor

	public ProducerRecord<K, V> onSend(ProducerRecord<K, V> record);
	public void onAcknowledgement(RecordMetadata metadata, Exception exception);
	public void close();

KafkaProducer在将消息序列化和计算分区之前调用生产者拦截器的onSend()方法来对消息进行相应的定制化操作 

## 7 消费者与消费组 ## 

