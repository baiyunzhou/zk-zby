package com.zby;

import java.io.IOException;

import org.apache.zookeeper.client.FourLetterWordMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author zhoubaiyun
 * @date 2018年5月23日
 * @Description 查看本地集群的状态
 */
public class FourLetterWordMainClusterStarter {
	private static final Logger LOG = LoggerFactory.getLogger(FourLetterWordMainClusterStarter.class);
	private static final String FOUR_LETTER = "srvr";

	public static void main(String[] args) {
		for (int i = 1; i <= 3; i++) {
			try {
				LOG.info("peer{}:\r\n{}", i, FourLetterWordMain.send4LetterWord("localhost", 2186 + i, FOUR_LETTER));
			} catch (IOException e) {
				LOG.error("节点状态查询失败", e);
				continue;
			}
		}

	}

}
