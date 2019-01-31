package com.zby.client;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

public class RefreshableClient implements Watcher {
	private List<String> children;
	private ZooKeeper zooKeeper;
	private String path;

	public RefreshableClient(ZooKeeper zooKeeper, String path) {
		this.zooKeeper = zooKeeper;
		this.path = path;
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println(event);
		if (EventType.NodeChildrenChanged == event.getType()) {
			try {
				children = zooKeeper.getChildren(path, this);
				System.out.println(children);
			} catch (KeeperException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void watch() {
		try {
			children = zooKeeper.getChildren(path, this);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		System.setProperty("zookeeper.sasl.client", "false");
		CountDownLatch countDownLatch = new CountDownLatch(1);
		ZooKeeper zooKeeper = new ZooKeeper("localhost:2181/", 6000, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println("接收到事件通知：" + event);
				if (event.getState() == KeeperState.SyncConnected) {
					System.out.println("客户端连接建立完成。");
					countDownLatch.countDown();
				}
			}
		}, false);
		System.out.println("客户端当前状态:" + zooKeeper.getState());
		if (zooKeeper.getState() != States.CONNECTED) {
			System.out.println("等待客户端连接完成。");
		}
		countDownLatch.await();
		RefreshableClient refreshableClient = new RefreshableClient(zooKeeper, "/");
		refreshableClient.watch();
		Thread.sleep(300000);
	}
}
