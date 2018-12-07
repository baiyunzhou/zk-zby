package com.zby;

import org.apache.zookeeper.server.quorum.QuorumPeerMain;

/**
 * 
 * @author zhoubaiyun
 * @date 2018年5月23日
 * @Description 通过配置文件启动单机模式ZooKeeper
 */
public class QuorumPeerMainSingleStarter {

	public static void main(String[] args) {
		QuorumPeerMain.main(new String[] { "src/main/resources/zoo.cfg" });
	}

}
