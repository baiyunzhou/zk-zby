package com.zby.zkclient;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

public class ZkClientMain {

	public static void main(String[] args) {
		ZkClient zkClient = new ZkClient("localhost:/");
		List<String> children = zkClient.getChildren("/a/b/c");
		System.out.println(children);
		zkClient.createPersistent("/a/b/c/d", true);
		zkClient.subscribeChildChanges("", new IZkChildListener() {
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {

			}
		});
		zkClient.close();
	}

}
