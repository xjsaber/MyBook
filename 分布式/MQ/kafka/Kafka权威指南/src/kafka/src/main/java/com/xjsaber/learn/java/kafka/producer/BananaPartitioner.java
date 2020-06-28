package com.xjsaber.learn.java.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

/**
 * 自定义分区器
 * @author xjsaber
 */
public class BananaPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int size = partitions.size();
        cluster.partitionCountForTopic(topic);
        if ((keyBytes == null) && !(key instanceof String)){
            throw new InvalidRecordException("We expect all messages to");
        }
        if ("Banana".equals(key)){
            return size;
        }
        return (Math.abs(Utils.murmur2(keyBytes)) % (size -  1));
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
