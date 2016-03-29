package cn.itcast.heima2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * 
 * Description: 线程内的共享变量-----》与线程绑定、同一个线程调用不同的模块 拿到的数据应该是一致的---》思想就是创建一个map机构，存放线程name和data、和ThreadLocal类型的效果
 * Created on:  2016年3月29日 下午10:15:08 
 * @author bbaiggey
 */
public class ThreadScopeShareData {

	private static int data = 0;
	private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();
	public static void main(String[] args) {
		for(int i=0;i<2;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() 
							+ " has put data :" + data);
					threadData.put(Thread.currentThread(), data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A{
		public void get(){
			int data = threadData.get(Thread.currentThread());
			System.out.println("A from " + Thread.currentThread().getName() 
					+ " get data :" + data);
		}
	}
	
	static class B{
		public void get(){
			int data = threadData.get(Thread.currentThread());			
			System.out.println("B from " + Thread.currentThread().getName() 
					+ " get data :" + data);
		}		
	}
}
