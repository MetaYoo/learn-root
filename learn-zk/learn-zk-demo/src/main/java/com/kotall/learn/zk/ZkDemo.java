package com.kotall.learn.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/8/22
 */
public class ZkDemo {

    private String connectStr = "localhost:2181";
    private ZooKeeper zkClient;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZkDemo demo = new ZkDemo();
        // 1. 创建连接
        demo.createConnection();
        //TimeUnit.SECONDS.sleep(2);
        // 2. 创建根节点
        demo.createNode("/p");
        // 3. 创建子节点
        TimeUnit.SECONDS.sleep(3);
        // 4. 创建子节点
        demo.createNode("/p/p1");
        TimeUnit.SECONDS.sleep(3);
    }

    private void createConnection() {
        try {
            if (null != zkClient) {
                this.zkClient.close();
            }
            zkClient = new ZooKeeper(connectStr, 1000, new ZkWatcher());
            countDownLatch.await();
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

        private AtomicInteger atomicInteger = new AtomicInteger();

        @Override
        public void process(WatchedEvent event) {
            Event.EventType eventType = event.getType();
            Event.KeeperState state = event.getState();
            String logPrefix = "【Watcher " + atomicInteger.getAndIncrement() +"】, state=" + state + ", eventType=" + eventType;
            System.out.println(logPrefix);
            if (Event.KeeperState.SyncConnected.equals(state)) {
                if (Event.EventType.None == eventType) {
                    System.out.println(" 创建连接成功！");
                    countDownLatch.countDown();
                }
                if (Event.EventType.NodeCreated.equals(eventType)) {
                    System.out.println(" 创建节点事件！");
                }
                if (Event.EventType.NodeDeleted.equals(eventType)) {
                    System.out.println(" 删除节点事件！");
                }
                if (Event.EventType.NodeDataChanged.equals(eventType)) {
                    System.out.println(" 节点数据变化事件！");
                }
                if (Event.EventType.NodeChildrenChanged.equals(eventType)) {
                    System.out.println(" 子节点变化事件！");
                }
            } else if (Event.KeeperState.Disconnected.equals(state)) {
                System.out.println(logPrefix + " 与zk服务器断开连接！");
            } else if (Event.KeeperState.AuthFailed.equals(state)) {
                System.out.println(logPrefix + " 权限检查失败！");
            } else if (Event.KeeperState.Expired.equals(state)) {
                System.out.println(logPrefix + " 会话失效");
            } else;


        }
    }
}
