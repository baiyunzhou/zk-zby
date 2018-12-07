package com.zby.jute;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.TreeMap;

import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.jute.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zby.jute.bean.Person;

public class JuteMain {
	private static final Logger LOG = LoggerFactory.getLogger(JuteMain.class);
	private static final String TMP_FILE_PATH = "E:\\tmp.txt";

	public static void main(String[] args) throws Exception {

		File file = prepareTmpFile();
		// write operation
		OutputStream outputStream = new FileOutputStream(file);
		BinaryOutputArchive binaryOutputArchive = BinaryOutputArchive.getArchive(outputStream);
		binaryOutputArchive.writeBool(true, "boolean");
		byte[] bytes = "jute".getBytes();
		binaryOutputArchive.writeBuffer(bytes, "buffer");
		binaryOutputArchive.writeDouble(3.1415926, "double");
		binaryOutputArchive.writeFloat(3.14f, "float");
		binaryOutputArchive.writeInt(9999, "int");

		Person person = new Person("zby", 24);
		binaryOutputArchive.writeRecord(person, "zby");

		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		map.put("num1", 1);
		map.put("num2", 2);
		Set<String> keys = map.keySet();
		binaryOutputArchive.startMap(map, "map");
		int i = 0;
		for (String key : keys) {
			String tag = i + "";
			binaryOutputArchive.writeString(key, tag);
			binaryOutputArchive.writeInt(map.get(key), tag);
			i++;
		}

		binaryOutputArchive.endMap(map, "map");

		// read operation
		InputStream inputStream = new FileInputStream(new File(TMP_FILE_PATH));
		BinaryInputArchive binaryInputArchive = BinaryInputArchive.getArchive(inputStream);

		System.out.println(binaryInputArchive.readBool("boolean"));
		System.out.println(new String(binaryInputArchive.readBuffer("buffer")));
		System.out.println(binaryInputArchive.readDouble("double"));
		System.out.println(binaryInputArchive.readFloat("float"));
		System.out.println(binaryInputArchive.readInt("int"));
		Person person2 = new Person();
		binaryInputArchive.readRecord(person2, "zby");
		System.out.println(person2);

		Index index = binaryInputArchive.startMap("map");
		int j = 0;
		while (!index.done()) {
			String tag = j + "";
			System.out.println("key = " + binaryInputArchive.readString(tag) + ", value = " + binaryInputArchive.readInt(tag));
			index.incr();
			j++;
		}
		outputStream.close();
		inputStream.close();
		if (file.delete()) {
			LOG.info("delete file {} success.", TMP_FILE_PATH);
		}
	}

	private static File prepareTmpFile() throws IOException {
		File file = new File(TMP_FILE_PATH);
		if (file.exists()) {
			LOG.info("文件{}已经存在。", file);
			if (file.delete()) {
				LOG.info("删除文件{}成功。", file);
			} else {
				LOG.info("删除文件{}失败，请手动删除。", file);
				System.exit(1);
			}
		}
		if (file.createNewFile()) {
			LOG.info("创建文件{}成功。", file);
		} else {
			LOG.info("创建文件{}失败，请手动创建。", file);
			System.exit(1);
		}
		return file;
	}
}