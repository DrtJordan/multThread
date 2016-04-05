package com.bg.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/**
 * 
 * Description: java NIOģ��
 * Created on:  2016��4��5�� ����11:38:40 
 * @author bbaiggey
 */
public class JavaNio {
	public static void main(String[] args) throws Exception {
		RandomAccessFile aFile = new RandomAccessFile("D://test.scala", "rw");
		FileChannel inChannel = aFile.getChannel();//��ȡһ���ܵ�
		 
		ByteBuffer buf = ByteBuffer.allocate(48);
		 
		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
		System.out.println("Read " + bytesRead);
//		ע�� buf.flip() �ĵ��ã����ȶ�ȡ���ݵ�Buffer��Ȼ��תBuffer,�����ٴ�Buffer�ж�ȡ����
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
