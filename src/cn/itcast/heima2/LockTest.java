package cn.itcast.heima2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * Description: Lock		ReentrantLock lock unlock  记得逻辑代码用try catch 包围最后在finally中释放锁资源
 * Created on:  2016年3月23日 下午11:55:02 
 * @author bbaiggey
 */
public class LockTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LockTest().init();
	}
	
	private void init(){
		final Outputer outputer = new Outputer();
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outputer.output("zhangxiaoxiang");
				}
				
			}
		}).start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outputer.output("lihuoming");
				}
				
			}
		}).start();
		
	}

	static class Outputer{
		 /**
	     * Creates an instance of {@code ReentrantLock}.
	     * This is equivalent to using {@code ReentrantLock(false)}.
	     */
		Lock lock = new ReentrantLock();
		public void output(String name){
			int len = name.length();
			lock.lock();
			try{//注意异常的处理，不然锁永远都无法释放 会造成死锁
				for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}finally{
				lock.unlock();
			}
		}
		
//		非静态的使用的是对象锁
		public synchronized void output2(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
//	static	使用的是类锁
		public static synchronized void output3(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
			}
			System.out.println();
		}	
	}
}
