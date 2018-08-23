package com.kotall.learn.zk;

import org.I0Itec.zkclient.*;
import org.apache.zookeeper.Watcher;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zpwang
 * @version 1.0.0
 */
public class ZkClientDemo {

    public static void main(String[] args) {
        try {

            ZkClient zkClient = new ZkClient(new ZkConnection("127.0.0.1:2181", 1000));

            // 监听节点变化
            zkClient.subscribeChildChanges("/p", new IZkChildListener(){
                @Override
                public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                    System.out.println("===== Create node path: " + parentPath);
                }
            });


            // 监听状态变化
            zkClient.subscribeStateChanges(new IZkStateListener() {

                @Override
                public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {
                    System.out.println("===== state changed: " + state);
                }

                @Override
                public void handleNewSession() throws Exception {
                    System.out.println("===== new Session !");
                }

                @Override
                public void handleSessionEstablishmentError(Throwable error) throws Exception {
                    System.out.println("===== sessionEstablishmentError !" + error);
                }
            });

            // 监听节点数据变化
            zkClient.subscribeDataChanges("/p", new IZkDataListener() {

                @Override
                public void handleDataChange(String dataPath, Object data) throws Exception {
                    System.out.println("===== 节点: " + dataPath + " 数据变化：" + data);
                }

                @Override
                public void handleDataDeleted(String dataPath) throws Exception {
                    System.out.println("===== 节点：" + dataPath + " 数据删除！");
                }
            });

//            zkClient.createPersistent("/p");
//            zkClient.createPersistent("/p/c1");
//            zkClient.createPersistent("/p/c2");
            zkClient.deleteRecursive("/p");
            TimeUnit.SECONDS.sleep(5);
            zkClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
