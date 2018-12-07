package com.zby.jute;

import org.apache.jute.compiler.generated.Rcc;

/**
 * 
 * @author zhoubaiyun
 * @date 2018年5月25日
 * @Description 生成序列化的Person类，生成的类在项目路径下，需要copy到需要的位置
 */
public class RccMain {

	public static void main(String[] args) {
		Rcc.main(new String[] { "-l", "java", "src/main/resources/person.jute" });

	}

}
