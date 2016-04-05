package com.bg.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/**
 * 
 * Description: java NIO模型
 * Created on:  2016年4月5日 上午11:38:40 
 * @author bbaiggey
 */
public class JavaNio {
	public static void main(String[] args) throws Exception {
		RandomAccessFile aFile = new RandomAccessFile("D://test.scala", "rw");
		FileChannel inChannel = aFile.getChannel();//获取一个管道
		 
		ByteBuffer buf = ByteBuffer.allocate(48);
		 
		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
		System.out.println("Read " + bytesRead);
//		注意 buf.flip() 的调用，首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据
		buf.flip();
		while(buf.hasRemaining()){
		System.out.print((char) buf.get());
		}
		buf.clear();
		bytesRead = inChannel.read(buf);
		}
		aFile.close();

	}

}
