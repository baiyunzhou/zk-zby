package com.zby;

import org.apache.zookeeper.ZooKeeperMain;

/**
 * 
 * @author zhoubaiyun
 * @date 2018年5月23日
 * @Description 连接本地集群，直接通过控制台输入命令，没有linux下显示友好
 */
public class ZooKeeperMainSingleStarter {

	public static void main(String[] args) throws Exception {
		System.setProperty("jline.WindowsTerminal.directConsole", "false");
		// ZooKeeperMain.main(new String[] { "-server", "10.40.10.205:2181,10.40.10.206:2181,10.40.10.207:2181" });
		ZooKeeperMain.main(new String[] { "-server", "localhost:2181" });
	}
}