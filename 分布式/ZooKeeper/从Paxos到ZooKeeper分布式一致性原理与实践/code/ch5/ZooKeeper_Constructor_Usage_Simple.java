package xjsaber.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xjsaber on 2017/6/1.
 * 创建一个最基本的ZooKeeper会话实例
 * Java API->创建连接->创建一个最基本的ZooKeeper会话实例
 */
public class ZooKeeper_Constructor_Usage_Simple implements Watcher{

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception{

        ZooKeeper zooKeeper = new ZooKeeper("192.168.129.128:2181", 5000, new ZooKeeper_Constructor_Usage_Simple());
        System.out.println(zooKeeper.getState());
        try {
            connectedSemaphore.await();
        } catch (InterruptedException ignored) {}
        System.out.println("ZooKeeper session established.");
    }

    public void process(WatchedEvent event){
        System.out.println("Receive watched event: " + event);
        if (Event.KeeperState.SyncConnected == event.getState()){
            connectedSemaphore.countDown();
        }
    }
}
