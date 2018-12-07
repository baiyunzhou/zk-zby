package com.zby;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperSingleClient {

	public static void main(String[] args) throws Exception {
		System.setProperty("zookeeper.sasl.client", "false");
		CountDownLatch countDownLatch = new CountDownLatch(1);
		ZooKeeper zooKeeper = new ZooKeeper("localhost:2181/a", 3000, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println("state:" + event.getState() + ",type:" + event.getType() + ",path:" + event.getPath());
				if (event.getType() == EventType.None && event.getState() == KeeperState.SyncConnected) {
					System.out.println("连接成功。");
					countDownLatch.countDown();
				}
			}
		}, false);
		countDownLatch.await();
		System.out.println("sessionId:" + zooKeeper.getSessionId());
		System.out.println("sessionPasswd:" + zooKeeper.getSessionPasswd());
		System.out.println("sessionTimeout:" + zooKeeper.getSessionTimeout());
		System.out.println("state:" + zooKeeper.getState());
		System.out.println("-----------------------------------------------------------");
		for (int i = 0; i < 70000; i++) {
			String realName = zooKeeper.create("/a", "p1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
			System.out.println(realName);
		}
		// String realName = zooKeeper.create("/persistent", "p1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		// System.out.println("node1:" + realName);
		// Stat stat = new Stat();
		// byte[] data = zooKeeper.getData(realName, false, stat);
		// System.out.println(stat);
		// System.out.println(data);
		// zooKeeper.delete(realName, 0);
		// Stat exists = zooKeeper.exists(realName, false);
		// System.out.println(exists);
		zooKeeper.close();
	}

}
