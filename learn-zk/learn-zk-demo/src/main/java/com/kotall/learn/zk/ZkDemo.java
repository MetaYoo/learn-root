package com.kotall.learn.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/8/22
 */
public class ZkDemo {

    private String connectStr = "localhost:2181";
    private ZooKeeper zkClient;

    public static void main(String[] args) throws Exception {
        ZkDemo demo = new ZkDemo();
        // 1. 创建连接
        demo.createConnection();
        TimeUnit.SECONDS.sleep(2);
        // 2. 创建根节点
        demo.createNode("/p");
        // 3. 创建子节点
    }

    private void createConnection() {
        try {
            if (null != zkClient) {
                this.zkClient.close();
            }
            zkClient = new ZooKeeper(connectStr, 1000, new ZkWatcher());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createNode(String path) {
        try {
            Stat stat = this.zkClient.exists(path, true);
            if (null == stat) {
                this.zkClient.create(path, (System.currentTimeMillis() + "").getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            } else {
                this.zkClient.delete(path, 0);
            }

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class ZkWatcher implements Watcher{

        @Override
        public void process(WatchedEvent event) {
            Event.EventType eventType = event.getType();
            Event.KeeperState state = event.getState();
            if (Event.KeeperState.SyncConnected.equals(state)) {
                if (null == eventType) {
                    System.out.println("创建连接成功！");
                }

                if (Event.EventType.NodeCreated.equals(state)) {
                    System.out.println("创建节点事件！");
                }
            }
        }
    }
}
