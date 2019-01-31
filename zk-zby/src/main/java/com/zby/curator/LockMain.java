package com.zby.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class LockMain {

	public static void main(String[] args) throws Exception {
		RetryPolicy RetryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework curator = CuratorFrameworkFactory.builder().connectString("localhost:2181/").sessionTimeoutMs(5000)
				.connectionTimeoutMs(3000).retryPolicy(RetryPolicy).build();
		curator.start();
		InterProcessLock interProcessLock = new InterProcessMutex(curator, "/lock");
		try {
			interProcessLock.acquire();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(curator.getChildren().forPath("/lock"));
		System.out.println(interProcessLock.isAcquiredInThisProcess());
		try {
			interProcessLock.release();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(curator.getChildren().forPath("/lock"));
		curator.close();
	}

}
