package com.zby;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.zookeeper.server.quorum.QuorumPeerMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author zhoubaiyun
 * @date 2018年5月23日
 * @Description 通过配置文件启动集群模式ZooKeeper
 */
public class QuorumPeerMainClusterStarter {
	private static final Logger LOG = LoggerFactory.getLogger(QuorumPeerMainClusterStarter.class);
	private static final String PATH_PREFIX = "src/main/resources/";
	private static final Integer PEER_NUM = 3;

	public static void main(String[] args) throws IOException {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		for (int i = 1; i <= PEER_NUM; i++) {
			File file = new File("./zk/zookeeper" + i + "/data");
			if (!file.exists()) {
				System.out.println("数据目录不存在，开始创建：" + file.getPath());
				file.mkdirs();
				File myid = new File(file, "myid");
				myid.createNewFile();
				FileOutputStream fileOutputStream = new FileOutputStream(myid);
				fileOutputStream.write(48 + i);
				fileOutputStream.flush();
				fileOutputStream.close();
			}

		}
		for (int i = 1; i <= PEER_NUM; i++) {
			executorService.submit(new Peer(PATH_PREFIX + "zoo" + i + ".cfg"));
		}

	}

	private static class Peer implements Runnable {
		private String config;

		public Peer(String config) {
			this.config = config;
		}

		@Override
		public void run() {
			LOG.info("Starting peer with config:{}", config);
			QuorumPeerMain.main(new String[] { config });
		}

	}
}
