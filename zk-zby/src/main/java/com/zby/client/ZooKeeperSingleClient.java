package com.zby.client;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.AsyncCallback.Children2Callback;
import org.apache.zookeeper.AsyncCallback.StringCallback;
import org.apache.zookeeper.AsyncCallback.VoidCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.data.Stat;

public class ZooKeeperSingleClient {

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
		System.out.println("会话ID:" + zooKeeper.getSessionId());
		System.out.println("会话秘钥:" + zooKeeper.getSessionPasswd());
		System.out.println("回话超时:" + zooKeeper.getSessionTimeout());
		System.out.println("客户端当前状态:" + zooKeeper.getState());
		printLine();
		testSyncCreatePersistent(zooKeeper, "/sync", "Hello,World!");

		testAsyncCreatePersistent(zooKeeper, "/async", "Hello,World!");
		printLine();
		List<String> children = zooKeeper.getChildren("/", true);
		System.out.println(children);
		List<String> children2 = zooKeeper.getChildren("/", new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println("接收到事件通知：" + event);
			}
		});
		System.out.println(children2);
		Stat stat = new Stat();
		List<String> children3 = zooKeeper.getChildren("/", true, stat);
		System.out.println(children3);
		System.out.println(stat);
		List<String> children4 = zooKeeper.getChildren("/", new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				// EventType.NodeChildrenChanged
				System.out.println("接收到事件通知：" + event);
			}
		}, stat);
		System.out.println(children4);
		System.out.println(stat);
		zooKeeper.getChildren("/", true, new Children2Callback() {
			@Override
			public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {

			}
		}, null);
		printLine();
		byte[] data = zooKeeper.getData("/", true, stat);
		// EventType.NodeDataChanged
		System.out.println(data);
		printLine();
		zooKeeper.setData("/", "".getBytes(), -1);
		printLine();
		Stat exists = zooKeeper.exists("/", true);
		System.out.println(exists);
		// EventType.NodeCreated,EventType.NodeDeleted,EventType.NodeDataChanged
		//
		zooKeeper.addAuthInfo("digest", "username:password".getBytes());
		zooKeeper.create("", "".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
		printLine();
		testSyncDeletePersistent(zooKeeper, "/sync", -1);
		printLine();
		testAsyncDeletePersistent(zooKeeper, "/async", -1);
		printLine();

		zooKeeper.close();
	}

	private static void testSyncCreatePersistent(ZooKeeper zooKeeper, String path, String data) {
		try {
			String result = zooKeeper.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			System.out.println("同步创建持久节点：路径：" + path + ",数据：" + data + "，结果为：" + result);
		} catch (KeeperException e) {
			System.out.println("同步创建持久节点失败：" + e);
		} catch (InterruptedException e) {
			System.out.println("同步创建持久节点失败：" + e);
		}

	}

	private static void testAsyncCreatePersistent(ZooKeeper zooKeeper, String path, String data) {
		System.out.println("异步创建持久节点：路径：" + path + ",数据：" + data + "，当前时间：" + System.currentTimeMillis());
		zooKeeper.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new StringCallback() {
			@Override
			public void processResult(int rc, String path, Object ctx, String name) {
				System.out.println("异步创建持久节点，处理结果:响应吗:" + rc + ",路径:" + path + ",上下文对象:" + ctx + ",名称：" + name + ",当前时间:"
						+ System.currentTimeMillis());
			}
		}, "处理结果的上下文对象");

	}

	private static void testSyncDeletePersistent(ZooKeeper zooKeeper, String path, int version) {
		try {
			zooKeeper.delete(path, version);
			System.out.println("同步删除持久节点成功：路径:" + path + ",版本:" + version);
		} catch (KeeperException e) {
			System.out.println("同步删除持久节点失败：" + e);
		} catch (InterruptedException e) {
			System.out.println("同步删除持久节点失败：" + e);
		}
	}

	private static void testAsyncDeletePersistent(ZooKeeper zooKeeper, String path, int version) {
		System.out.println("异步删除持久节点：路径：" + path + ",版本:" + version + "，当前时间：" + System.currentTimeMillis());
		zooKeeper.delete(path, version, new VoidCallback() {
			@Override
			public void processResult(int rc, String path, Object ctx) {
				System.out.println("异步删除持久节点，处理结果rc:" + rc + ",路径:" + path + ",上下文对象:" + ctx + ",当前时间:" + System.currentTimeMillis());

			}
		}, "处理结果的上下文对象");
	}

	private static void printLine() {
		System.out.println("-------------------------------------------------");
	}
}
