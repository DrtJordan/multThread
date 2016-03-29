package cn.itcast.heima2;
/**
 * 
 * Description: ���̹߳�������
 * 1�����������ݷ�װ�������У��������ݶ��󴫵ݸ�Runnable�����У������Runnable�н��в�����Ӧ�ķ��� ֮�����Runnable���󴫵ݸ��߳�ִ��
 * 2������ЩRunnable��Ϊĳ������ڲ��ࡢ���������ݷ�װ�ɶ��󣬽����������Ϊ�ⲿ��ĳ�Ա������������final���ε��ڲ���Ա�������ڲ�������ⲿ��ĳ�Ա��Ҫ��final���Ρ�
 * Created on:  2016��3��27�� ����6:36:04 
 * @author bbaiggey
 */
public class MultiThreadShareData {

//	private static ShareData1 data1 = new ShareData1();
	
	public static void main(String[] args) {
		ShareData1 data2 = new ShareData1();
//		1�����������ݷ�װ�������У��������ݶ��󴫵ݸ�Runnable�����У������Runnable�н��в�����Ӧ�ķ��� ֮�����Runnable���󴫵ݸ��߳�ִ��
		new Thread(new MyRunnable1(data2)).start();
		new Thread(new MyRunnable2(data2)).start();
		
//		2������ЩRunnable��Ϊĳ������ڲ��ࡢ���������ݷ�װ�ɶ��󣬽����������Ϊ�ⲿ��ĳ�Ա������������final���ε��ڲ���Ա�������ڲ�������ⲿ��ĳ�Ա��Ҫ��final���Ρ�
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