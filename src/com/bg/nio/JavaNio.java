package com.bg.nio;

import java.io.IOException;
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
//		��ʹ��FileChannel֮ǰ�������ȴ��������ǣ������޷�ֱ�Ӵ�һ��FileChannel����Ҫͨ��ʹ��һ��InputStream��OutputStream��RandomAccessFile����ȡһ��FileChannelʵ����������ͨ��RandomAccessFile��
		RandomAccessFile aFile = new RandomAccessFile("D://test.scala", "rw");
		FileChannel inChannel = aFile.getChannel();//��ȡһ���ܵ�
		 
		ByteBuffer buf = ByteBuffer.allocate(48);
//		 ���ö��read()����֮һ��FileChannel�ж�ȡ����
//		���ȣ�����һ��Buffer����FileChannel�ж�ȡ�����ݽ�������Buffer�С�
//		Ȼ�󣬵���FileChannel.read()�������÷��������ݴ�FileChannel��ȡ��Buffer�С�read()�������ص�intֵ��ʾ���ж����ֽڱ�������Buffer�С��������-1����ʾ�����ļ�ĩβ��
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
	/**
	 * 
	 *ע��FileChannel.write()����whileѭ���е��õġ���Ϊ�޷���֤write()����һ������FileChannelд������ֽڣ������Ҫ�ظ�����write()������ֱ��Buffer���Ѿ�û����δд��ͨ�����ֽڡ�
	 */
	public void w() throws Exception{
		RandomAccessFile aFile = new RandomAccessFile("D://test.scala", "rw");
		FileChannel channel = aFile.getChannel();//��ȡһ���ܵ�
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		while(buf.hasRemaining()) {
		    channel.write(buf);
		}
		channel.close();

	}
	

}
