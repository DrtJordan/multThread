package cn.itcast.heima2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		�����̶���С���̳߳�,���̳߳��е��߳�����֮��ͻ�ȴ����߳���Դ�ͷŲ��ܱ�ִ��.,�����쳣�ͻ���ֹ,֮��ᴴ���µ��̳߳���
//		ExecutorService threadPool = Executors.newFixedThreadPool(3);		//�̳߳� ������3
//		���ÿ��õ��߳�,������60��û�б�ʹ�þͻᱻ�Ƴ�,�����е��̲߳���ʱ,�ᴴ���µ��߳�
//		ExecutorService threadPool = Executors.newCachedThreadPool();	//��������̳߳�
		/*ExecutorService threadPool = Executors.newSingleThreadExecutor();	//���̳߳�
		for(int i=1;i<=10;i++){
			final int task = i;
			threadPool.execute(new Runnable(){
				@Override
				public void run() {
					for(int j=1;j<=10;j++){
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + " is looping of " + j + " for  task of " + task);
					}
				}
			});
		}
		System.out.println("all of 10 tasks have committed! ");*/
//		threadPool.shutdownNow();
		
//		����һ��ָ�������ĵ����̳߳�				����,��һ��ִ�еļ��ʱ��,����ظ�һ�Σ�ʱ�䵥λ
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(
				new Runnable(){
					@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+"ִ��!");
					
				}},
				6,
				2,
				TimeUnit.SECONDS);
	}

}
