package com.zby.curator;

import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorMain {

	public static void main(String[] args) throws Exception {
		RetryPolicy RetryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("localhost:2181/", 5000, 3000, RetryPolicy);
		curatorFramework.start();
		List<String> children = curatorFramework.getChildren().forPath("/");
		System.out.println(children);
		curatorFramework.close();

		CuratorFramework curator = CuratorFrameworkFactory.builder().connectString("localhost:2181/").sessionTimeoutMs(5000)
				.connectionTimeoutMs(3000).retryPolicy(RetryPolicy).namespace("zby").build();
		curator.start();
		NodeCache nodeCache = new NodeCache(curator, "/", false);
		nodeCache.start(true);
		nodeCache.getListenable().addListener(new NodeCacheListener() {
			@Override
			public void nodeChanged() throws Exception {
				System.out.println(nodeCache.getCurrentData());
			}
		});
		curator.create().forPath("/name", "zby".getBytes());
		curator.create().forPath("/age", "25".getBytes());
		curator.delete().guaranteed().deletingChildrenIfNeeded().forPath("/");
		Thread.sleep(3000);
		nodeCache.close();
		curator.close();
	}

}
