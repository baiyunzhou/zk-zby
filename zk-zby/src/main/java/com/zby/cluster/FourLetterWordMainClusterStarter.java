package com.zby.cluster;

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
	public static final String CONF = "conf";
	public static final String CONS = "cons";
	public static final String CRST = "crst";
	public static final String DUMP = "dump";
	public static final String ENVI = "envi";
	public static final String ROUK = "rouk";
	public static final String STAT = "stat";
	public static final String SRVR = "srvr";
	public static final String SRST = "srst";
	public static final String WCHS = "wchs";
	public static final String WCHP = "wchp";
	public static final String MNTR = "mntr";

	public static void main(String[] args) {
		for (int i = 1; i <= 3; i++) {
			try {
				LOG.info("CONF:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181 + i, CONF));
				LOG.info("CONS:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181 + i, CONS));
				LOG.info("CRST:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181 + i, CRST));
				LOG.info("DUMP:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181 + i, DUMP));
				LOG.info("ENVI:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181 + i, ENVI));
				LOG.info("ROUK:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181 + i, ROUK));
				LOG.info("STAT:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181 + i, STAT));
				LOG.info("SRVR:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181 + i, SRVR));
				LOG.info("SRST:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181 + i, SRST));
				LOG.info("WCHS:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181 + i, WCHS));
				LOG.info("WCHP:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181 + i, WCHP));
				LOG.info("MNTR:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181 + i, MNTR));
			} catch (IOException e) {
				LOG.error("节点状态查询失败", e);
				continue;
			}
		}

	}

}
