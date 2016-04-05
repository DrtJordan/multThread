package com.bg.nio;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
/**
 * 
 * Description: java NIOģ��
 * Created on:  2016��4��5�� ����11:38:40 
 * @author bbaiggey
 */
public class JavaNio2Channel {
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
	public void FileChannelWrite() throws Exception{
//		FileChannelֻ������ģʽ
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
	
	
	public void SocketChannelWrite() throws Exception{
//		����ģʽSocketChannel
		/*SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));

		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		while(buf.hasRemaining()) {
			socketChannel.write(buf);
		}
		socketChannel.close();*/
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));
		
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		while(! socketChannel.finishConnect() ){
			    //wait, or do something else...
			}

		socketChannel.close();
		
	}
	/**
	 * 
	 * java NIO�е� ServerSocketChannel ��һ�����Լ����½�����TCP���ӵ�ͨ��, �����׼IO�е�ServerSocketһ����
	 */
	public void ServerSocketChannelWrite() throws Exception{
//		����ģʽ
//		ͨ������ ServerSocketChannel.open() ��������ServerSocketChannel.�磺
		/*ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(9999));
//		ͨ�� ServerSocketChannel.accept() ���������½��������ӡ��� accept()�������ص�ʱ��,������һ�������½��������ӵ� SocketChannel�����, accept()������һֱ�������������ӵ��
//		ʹ��while��Ŀ����  ͨ������ֻ����һ������ ���Ի�һֱѭ����ȥ
		while(true){
		    SocketChannel socketChannel =
		            serverSocketChannel.accept();

		    //do something with socketChannel...
		}*/
//		������ģʽ
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(9999));
//		���óɷ�����ģʽ
		serverSocketChannel.configureBlocking(false);
		while(true){
		    SocketChannel socketChannel =
		            serverSocketChannel.accept();
		    if(socketChannel != null){
		        //do something with socketChannel...
		    }
		}

		
		
	}
	
	

}