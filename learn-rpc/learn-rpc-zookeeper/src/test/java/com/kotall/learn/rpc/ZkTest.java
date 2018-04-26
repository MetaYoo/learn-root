package com.kotall.learn.rpc;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/4/26
 */
public class ZkTest {

    private static final String connStr = "127.0.0.1:2181";
    private static final int sessionTimeout = 10000;


    @Test
    public void testConn() throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper(connStr, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    latch.countDown();
                }
            }
        });
        latch.await();
        long sessionId = zk.getSessionId();
        System.out.println(sessionId);
    }


    @Test
    public void testCreateNote() throws Exception {
        ZooKeeper zk = new ZooKeeper(connStr,sessionTimeout,null);
        String result = zk.create("/testRoot", "testRoot".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(result);
    }

    @Test
    public void testGetNote() throws Exception {
        ZooKeeper zk = new ZooKeeper(connStr,sessionTimeout,null);
        String result = zk.create("/testRoot", "testRoot".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(result);


        Stat stat = new Stat();
        byte[] rs = zk.getData("/testRoot",true,stat);

        System.out.println(new String(rs));
    }


    @Test
    public void testWatchNode() throws Exception {
        ZooKeeper zk = new ZooKeeper(connStr,sessionTimeout,null);
        List<String> children = zk.getChildren("/testRoot",new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println("this is children node event");
                System.out.println(event);
            }
        });

        zk.create("/testRoot/child1",
                "child1_data".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);

    }
}
