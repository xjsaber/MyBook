package com.xjsaber.java.zookeeper.code.ch3;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author xjsaber
 */
public class Master implements Watcher {
    private ZooKeeper zk;
    private String hostPort;

    private Master(String hostPort){
        this.hostPort = hostPort;
    }

    private void startZK() throws IOException {
        zk = new ZooKeeper(hostPort, 15000, this);
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Master m = new Master(args[0]);

        m.startZK();

        // wait for a bit
        Thread.sleep(60000);
    }
}
