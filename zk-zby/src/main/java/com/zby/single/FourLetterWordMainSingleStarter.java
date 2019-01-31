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

	public static void main(String[] args) throws Exception {
		LOG.info("CONF:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181, CONF));
		LOG.info("CONS:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181, CONS));
		LOG.info("CRST:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181, CRST));
		LOG.info("DUMP:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181, DUMP));
		LOG.info("ENVI:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181, ENVI));
		LOG.info("ROUK:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181, ROUK));
		LOG.info("STAT:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181, STAT));
		LOG.info("SRVR:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181, SRVR));
		LOG.info("SRST:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181, SRST));
		LOG.info("WCHS:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181, WCHS));
		LOG.info("WCHP:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181, WCHP));
		LOG.info("MNTR:\r\n{}", FourLetterWordMain.send4LetterWord("localhost", 2181, MNTR));

	}

}
