#### test-topic ####

bin/kafka-topics.sh  --zookeeper 192.168.33.142:2181,192.168.33.140:2181,192.168.33.146:2181 --create  --topic test-topic --partitions 3 --replication-factor 3


:<<'
[root@localhost kafka]# bin/kafka-topics.sh  --zookeeper 192.168.33.142:2821,192.168.33.140:2821,192.168.33.146:2821 --create  --topic test-topic --partitions 3 --replication-factor 3
[2019-09-04 13:36:34,006] WARN Client session timed out, have not heard from server in 10049ms for sessionid 0x0 (org.apache.zookeeper.ClientCnxn)
[2019-09-04 13:36:45,674] WARN Client session timed out, have not heard from server in 11044ms for sessionid 0x0 (org.apache.zookeeper.ClientCnxn)
[2019-09-04 13:36:56,562] WARN Client session timed out, have not heard from server in 10012ms for sessionid 0x0 (org.apache.zookeeper.ClientCnxn)
Exception in thread "main" kafka.zookeeper.ZooKeeperClientTimeoutException: Timed out waiting for connection while in state: CONNECTING
	at kafka.zookeeper.ZooKeeperClient.$anonfun$waitUntilConnected$3(ZooKeeperClient.scala:258)
	at scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.java:23)
	at kafka.utils.CoreUtils$.inLock(CoreUtils.scala:253)
	at kafka.zookeeper.ZooKeeperClient.waitUntilConnected(ZooKeeperClient.scala:254)
	at kafka.zookeeper.ZooKeeperClient.<init>(ZooKeeperClient.scala:112)
	at kafka.zk.KafkaZkClient$.apply(KafkaZkClient.scala:1826)
	at kafka.admin.TopicCommand$ZookeeperTopicService$.apply(TopicCommand.scala:280)
	at kafka.admin.TopicCommand$.main(TopicCommand.scala:53)
	at kafka.admin.TopicCommand.main(TopicCommand.scala)
'

bin/kafka-topics.sh --zookeeper 192.168.33.142:2181,192.168.33.140:2181,192.168.33.146:2181 --list 
# test-topic
bin/kafka-topics.sh --zookeeper 192.168.33.142:2181,192.168.33.140:2181,192.168.33.146:2181 --describe --topic test-topic
# 结果


# ERROR [ReplicaFetcher replicaId=2, leaderId=1, fetcherId=0] Error for partition test-topic-1 at offset 0 (kafka.server.ReplicaFetcherThread)
# org.apache.kafka.common.errors.UnknownTopicOrPartitionException: This server does not host this topic-partition.

bin/kafka-topics.sh --zookeeper 192.168.33.142:2181,192.168.33.140:2181,192.168.33.146:2181 --delete --topic test-topic
# Topic test-topic is marked for deletion.
# Note: This will have no impact if delete.topic.enable is not set to true.

#### Send Message ####

bin/kafka-console-production.sh --broker-list 192.168.33.142:9092,192.168.33.140:9092,192.168.33.146:9092 --topic test-topic
bin/kafka-console-consumer.sh --bootstrap-server 192.168.33.142:9092,192.168.33.140:9092,192.168.33.146:9092 --topic test-topic --from-beginning

# 生产者吞吐量测试
bin/kafka-producer-pref-test.sh --topic test-topic --num-records 500000 --record -size 200 --throughput -1 --producer-props bootstrap.servers=192.168.33.142:9092,192.168.33.140:9092,192.168.33.146:9092 acks
# 消费者吞吐量测试
bin/kafka-consumer-pref-test.sh --broker-list 192.168.33.142:9092,192.168.33.140:9092,192.168.33.146:9092 --message-size 200 --messages 500000 --topic test-topic