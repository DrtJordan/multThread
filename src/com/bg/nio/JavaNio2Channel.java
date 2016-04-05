package com.bg.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
/**
 * 
 * Description: java NIO模型
 * Created on:  2016年4月5日 上午11:38:40 
 * @author bbaiggey
 */
public class JavaNio2Channel {
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
	public void FileChannelWrite() throws Exception{
//		FileChannel只有阻塞模式
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
	
	
	public void SocketChannelWrite() throws Exception{
//		阻塞模式SocketChannel
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
	 * java NIO中的 ServerSocketChannel 是一个可以监听新进来的TCP连接的通道, 就像标准IO中的ServerSocket一样。
	 */
	public void ServerSocketChannelWrite() throws Exception{
//		阻塞模式
//		通过调用 ServerSocketChannel.open() 方法来打开ServerSocketChannel.如：
		/*ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(9999));
//		通过 ServerSocketChannel.accept() 方法监听新进来的连接。当 accept()方法返回的时候,它返回一个包含新进来的连接的 SocketChannel。因此, accept()方法会一直阻塞到有新连接到达。
//		使用while的目的是  通常不会只监听一个连接 所以会一直循环下去
		while(true){
		    SocketChannel socketChannel =
		            serverSocketChannel.accept();

		    //do something with socketChannel...
		}*/
//		非阻塞模式
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(9999));
//		设置成非阻塞模式
		serverSocketChannel.configureBlocking(false);
		while(true){
		    SocketChannel socketChannel =
		            serverSocketChannel.accept();
		    if(socketChannel != null){
		        //do something with socketChannel...
		    }
		}

		
		
	}
	

	/**
	 * 
	 * Description: TODO
	 * @author bbaiggey
	 * @param  @throws Exception
	 * @return void
	 * @throws
	 */
	public void DatagramChannelWrite() throws Exception{
		
	/*	DatagramChannel channel = DatagramChannel.open();
		channel.socket().bind(new InetSocketAddress(9999));
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		channel.receive(buf);*/
		
		DatagramChannel channel = DatagramChannel.open();
		channel.socket().bind(new InetSocketAddress(9999));
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		int bytesSent = channel.send(buf, new InetSocketAddress("jenkov.com", 80));

//		连接到特定的地址
//		可以将DatagramChannel“连接”到网络中的特定地址的。由于UDP是无连接的，连接到特定地址并不会像TCP通道那样创建一个真正的连接。而是锁住DatagramChannel ，让其只能从特定地址收发数据。
		channel.connect(new InetSocketAddress("jenkov.com", 80));
		int bytesRead = channel.read(buf);
		int bytesWritten = channel.write(buf);

		

		
	}
	/**
	 * 
	 * Description: java NIO 管道是2个线程之间的单向数据连接。Pipe有一个source通道和一个sink通道
	 * @author bbaiggey
	 * @param  @throws Exception
	 * @return void
	 * @throws
	 */
	public void Pipe() throws Exception{
		Pipe pipe = Pipe.open();
//		要向管道写数据，需要访问sink通道。像这样：
		Pipe.SinkChannel sinkChannel = pipe.sink();
		
		
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		while(buf.hasRemaining()) {
//			通过调用SinkChannel的write()方法，将数据写入SinkChannel,像这样：
		    sinkChannel.write(buf);
		}

		/*从管道读取数据
		从读取管道的数据，需要访问source通道，像这样：*/
		Pipe.SourceChannel sourceChannel = pipe.source();
//		调用source通道的read()方法来读取数据，像这样：
		ByteBuffer buf1 = ByteBuffer.allocate(48);
		int bytesRead = sourceChannel.read(buf1);
//		read()方法返回的int值会告诉我们多少字节被读进了缓冲区。
	}
	
	

}
