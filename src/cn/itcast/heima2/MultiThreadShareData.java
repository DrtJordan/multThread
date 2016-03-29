package cn.itcast.heima2;
/**
 * 
 * Description: 多线程共享数据
 * 1、将共享数据封装到对象中，将该数据对象传递给Runnable对象中，在这个Runnable中进行操作相应的方法 之后将这个Runnable对象传递给线程执行
 * 2、将这些Runnable作为某个类的内部类、将共享数据封装成对象，将这个对象作为外部类的成员变量，或者用final修饰的内部成员变量【内部类访问外部类的成员需要加final修饰】
 * Created on:  2016年3月27日 上午6:36:04 
 * @author bbaiggey
 */
public class MultiThreadShareData {

//	private static ShareData1 data1 = new ShareData1();
	
	public static void main(String[] args) {
		ShareData1 data2 = new ShareData1();
//		1、将共享数据封装到对象中，将该数据对象传递给Runnable对象中，在这个Runnable中进行操作相应的方法 之后将这个Runnable对象传递给线程执行
		new Thread(new MyRunnable1(data2)).start();
		new Thread(new MyRunnable2(data2)).start();
		
//		2、将这些Runnable作为某个类的内部类、将共享数据封装成对象，将这个对象作为外部类的成员变量，或者用final修饰的内部成员变量【内部类访问外部类的成员需要加final修饰】
		/* final ShareData1 data1 = new ShareData1();
		new Thread(new Runnable(){
			@Override
			public void run() {
				data1.decrement();
				
			}
		}).start();
		new Thread(new Runnable(){
			@Override
			public void run() {
				data1.increment();
				
			}
		}).start();*/

	}

}
	
	class MyRunnable1 implements Runnable{
		private ShareData1 data1;
		public MyRunnable1(ShareData1 data1){
			this.data1 = data1;
		}
		public void run() {
			data1.decrement();
			
			
		}
	}
	
	class MyRunnable2 implements Runnable{
		private ShareData1 data1;
		public MyRunnable2(ShareData1 data1){
			this.data1 = data1;
		}
		public void run() {
			data1.increment();
		}
	}

	class ShareData1 /*implements Runnable*/{
/*		private int count = 100;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				count--;
			}
		}*/
		
		
		private int j = 0;
		public synchronized void increment(){
			System.out.println(j++);
		}
		
		public synchronized void decrement(){
			System.out.println(j--);
		}
	}