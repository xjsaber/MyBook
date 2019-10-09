package com.xjsaber.learn.java.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

/**
 * @author xjsaber
 */
public class KafkaConsumerAnalysis {

    private static final String BROKER_LIST = "192.168.33.142:9092,192.168.33.196:9092,192.168.33.146:9092";
    private static final String TOPIC ="test-topic";
    private static final String GROUP_ID = "group.demo";

    private static Properties initConfig(){
        Properties props = new Properties();
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("bootstrap.servers", BROKER_LIST);
        props.put("group.id", GROUP_ID);
        props.put("client.id", "consumer.client.id.demo");
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(TOPIC));

        try {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord record: records){
                System.out.println("topic = " + record.topic()
                        + ", partition = "+ record.partition()
                        + ", offset = " + record.offset());
                System.out.println("key = " + record.key()
                        + ", value = " + record.value());
                //do something to process record.
            }
        } catch (Exception ex){
            log.error("occur exception ", ex);
        } finally {
            consumer.close();
        }
    }
}
