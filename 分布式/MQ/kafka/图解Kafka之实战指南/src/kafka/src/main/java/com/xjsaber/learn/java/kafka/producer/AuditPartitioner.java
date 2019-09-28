package com.xjsaber.learn.java.kafka.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AuditPartitioner implements Partitioner {

    private Random random;

    public int partition(String topic, Object keyObj, byte[] keyBytes, Object valueObj, byte[] valueBytes, Cluster cluster) {
        String key = (String)keyObj;
        List<PartitionInfo> partitionerList = cluster.availablePartitionsForTopic(topic);
        int partitionCount = partitionerList.size();
        int auditPartition = partitionCount - 1;

        if (key == null || key.isEmpty() || !key.contains("audit")){
            return random.nextInt(partitionCount - 1);
        } else {
            return auditPartition;
        }
    }

    public void close() {
        // 必要的资源的清理工作
    }

    public void configure(Map<String, ?> map) {
        // 该方法实现必要资源的初始化工作
        random = new Random();
    }
}
