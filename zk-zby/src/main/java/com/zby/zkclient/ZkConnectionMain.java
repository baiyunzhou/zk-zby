package com.zby.zkclient;

import java.util.List;

import org.I0Itec.zkclient.IZkConnection;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class ZkConnectionMain {

	public static void main(String[] args) throws Exception {
		IZkConnection zkConnection = new ZkConnection("localhost:2181/");
		zkConnection.connect(new Watcher() {

			@Override
			public void process(WatchedEvent event) {
				System.out.println("event:" + event);
			}
		});
		System.out.println("state:" + zkConnection.getZookeeperState());
		List<String> children = zkConnection.getChildren("/", false);
		System.out.println(children);
		zkConnection.close();
	}

}
