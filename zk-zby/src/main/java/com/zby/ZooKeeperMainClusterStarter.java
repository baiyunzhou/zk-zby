package com.zby;

import org.apache.zookeeper.ZooKeeperMain;

/**
 * 
 * @author zhoubaiyun
 * @date 2018年5月23日
 * @Description 连接本地单机，直接通过控制台输入命令，没有linux下显示友好
 */
public class ZooKeeperMainClusterStarter {

	public static void main(String[] args) throws Exception {
		System.setProperty("jline.WindowsTerminal.directConsole", "false");
		ZooKeeperMain.main(new String[] { "-server", "localhost:2187,localhost:2188,localhost:2189/" });
	}
}