package com.zby.single;

import org.apache.zookeeper.client.FourLetterWordMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author zhoubaiyun
 * @date 2018年5月23日
 * @Description 查看本地集群的状态
 */
public class FourLetterWordMainSingleStarter {
	private static final Logger LOG = LoggerFactory.getLogger(FourLetterWordMainSingleStarter.class);
	private static final String FOUR_LETTER = "srvr";

	public static void main(String[] args) throws Exception {
		LOG.info("peer:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181, FOUR_LETTER));
	}

}
