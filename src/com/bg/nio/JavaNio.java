package com.bg.nio;

import java.io.IOException;
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
//		在使用FileChannel之前，必须先打开它。但是，我们无法直接打开一个FileChannel，需要通过使用一个InputStream、OutputStream或RandomAccessFile来获取一个FileChannel实例。下面是通过RandomAccessFile打开
		RandomAccessFile aFile = new RandomAccessFile("D://test.scala", "rw");
		FileChannel inChannel = aFile.getChannel();//获取一个管道
		 
		ByteBuffer buf = ByteBuffer.allocate(48);
//		 调用多个read()方法之一从FileChannel中读取数据
//		首先，分配一个Buffer。从FileChannel中读取的数据将被读到Buffer中。
//		然后，调用FileChannel.read()方法。该方法将数据从FileChannel读取到Buffer中。read()方法返回的int值表示了有多少字节被读到了Buffer中。如果返回-1，表示到了文件末尾。
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
	/**
	 * 
	 *注意FileChannel.write()是在while循环中调用的。因为无法保证write()方法一次能向FileChannel写入多少字节，因此需要重复调用write()方法，直到Buffer中已经没有尚未写入通道的字节。
	 */
	public void w() throws Exception{
		RandomAccessFile aFile = new RandomAccessFile("D://test.scala", "rw");
		FileChannel channel = aFile.getChannel();//获取一个管道
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
