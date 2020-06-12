package com.xjsaber.learn.kafka;

//import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Properties;

/**
 * @author xjsaber
 */
public class KafkaProducer {

    private String topic = "Topic1";
    private Producer<String, String> producer;
    private Cluster cluster;
    public KafkaProducer(){
        Properties prop = new Properties();
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(prop);
    }

    public int myPartitions() {
        String key = "hashCode";
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        return Math.abs(key.hashCode()) % partitions.size();
    }


//    public void send() {
//        producer.send(new ProducerRecord<String, String>(), new Callback() {
//            @Override
//            public void onCompletion(RecordMetadata metadata, Exception exception) {
//
//            }
//        });
//    }
}
