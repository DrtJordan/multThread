package com.bg.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
 * Description: java NIOģ��	��׼IO��д
 * Created on:  2016��4��5�� ����11:38:40 
 * @author bbaiggey
 */
public class JavaNio2Channel {
	public static void main(String[] args) throws Exception {
//		Nio();
		JavaNio2Channel javaNio2Channel = new JavaNio2Channel();
//		String str = javaNio2Channel.BufferedReaderDemo("D:\\j.txt");
//		String str = javaNio2Channel.FileInputStreamDemo("D:\\j.txt");
		javaNio2Channel.readInputStream();

	}
	private static void Nio() throws FileNotFoundException, IOException {
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

//		���ӵ��ض��ĵ�ַ
//		���Խ�DatagramChannel�����ӡ��������е��ض���ַ�ġ�����UDP�������ӵģ����ӵ��ض���ַ��������TCPͨ����������һ�����������ӡ�������סDatagramChannel ������ֻ�ܴ��ض���ַ�շ����ݡ�
		channel.connect(new InetSocketAddress("jenkov.com", 80));
		int bytesRead = channel.read(buf);
		int bytesWritten = channel.write(buf);

		

		
	}
	/**
	 * 
	 * Description: java NIO �ܵ���2���߳�֮��ĵ����������ӡ�Pipe��һ��sourceͨ����һ��sinkͨ��
	 * @author bbaiggey
	 * @param  @throws Exception
	 * @return void
	 * @throws
	 */
	public void Pipe() throws Exception{
		Pipe pipe = Pipe.open();
//		Ҫ��ܵ�д���ݣ���Ҫ����sinkͨ������������
		Pipe.SinkChannel sinkChannel = pipe.sink();
		
		
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		while(buf.hasRemaining()) {
//			ͨ������SinkChannel��write()������������д��SinkChannel,��������
		    sinkChannel.write(buf);
		}

		/*�ӹܵ���ȡ����
		�Ӷ�ȡ�ܵ������ݣ���Ҫ����sourceͨ������������*/
		Pipe.SourceChannel sourceChannel = pipe.source();
//		����sourceͨ����read()��������ȡ���ݣ���������
		ByteBuffer buf1 = ByteBuffer.allocate(48);
		int bytesRead = sourceChannel.read(buf1);
//		read()�������ص�intֵ��������Ƕ����ֽڱ������˻�������
	}
	
/**
 * java ��׼IO������д
 * @throws Exception 
 */
	public void  readInputStream() throws Exception{
		
		InputStream input = new FileInputStream("D:\\j.txt");
		/**
		 * �����
		 */
//		InputStream input = new BufferedInputStream(new FileInputStream("c:\\data\\input-file.txt"));
		byte[] buf = new byte[1024];
		 StringBuffer sb=new StringBuffer();
		int data = input.read(buf); //����ֵ�Ƕ�ȡ�ĳ���
		while(data != -1){//��ȡ��ĩβ���ص���-1
		     
		        sb.append(new String(buf));  
		        buf=new byte[1024];//�������ɣ�������ϴζ�ȡ�������ظ�
		        data = input.read(buf);
		        
		}
		System.out.println(sb.toString());

	}
	
	   public String FileInputStreamDemo(String path) throws IOException{
	         File file=new File(path);
	         if(!file.exists()||file.isDirectory())
	             throw new FileNotFoundException();
	         FileInputStream fis=new FileInputStream(file);
	         byte[] buf = new byte[1024];
	         StringBuffer sb=new StringBuffer();
	         while((fis.read(buf))!=-1){
	             sb.append(new String(buf));    
	             buf=new byte[1024];//�������ɣ�������ϴζ�ȡ�������ظ�
	         }
	         return sb.toString();
	     }
	
	public String BufferedReaderDemo(String path) throws IOException{
		
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br=new BufferedReader(new FileReader(file));
        String temp=null;
        StringBuffer sb=new StringBuffer();
        temp=br.readLine();
        while(temp!=null){
            sb.append(temp+"\n");
            temp=br.readLine();
        }
        return sb.toString();
    }
	
	public void  writeOutputStream() throws Exception{
		
		OutputStream output = new FileOutputStream("c:\\data\\input-file.txt");
		output.write("Hello World".getBytes());
		output.close();

		
		
	}
	

}
