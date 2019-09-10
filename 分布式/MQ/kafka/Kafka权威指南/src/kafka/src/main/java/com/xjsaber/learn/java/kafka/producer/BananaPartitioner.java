package com.xjsaber.learn.java.kafka.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

/**
 * 自定义分区器
 */
public class BananaPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        cluster.partitionCountForTopic(topic);
        if ((keyBytes == null) && !(key instanceof String)){
            throw new In
        }
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
