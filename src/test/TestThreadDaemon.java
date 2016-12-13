package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * Description: 测试守护线程
 * Created on:  2016年12月13日 下午5:40:25 
 * @author bbaiggey
 * @github https://github.com/bbaiggey
 */
public class TestThreadDaemon {
	public static void main(String[] args) {
		//Create a new executor service to drain the work queue.
	/*	ThreadFactory tf = new ThreadFactoryBuilder()
        .setNameFormat("Task Executor #%d")
        .build();*/
		ExecutorService newSingleThreadExecutor = /*Executors.newSingleThreadExecutor();*/ 
				
													Executors.newFixedThreadPool(1);
//		Executors.newFixedThreadPool(1,tf);
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(100);
					System.out.println("task 1"+Thread.currentThread().getName());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		Runnable runnable1 = new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(100);
					System.out.println("task 2"+Thread.currentThread().getName());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		newSingleThreadExecutor.execute(runnable);
		newSingleThreadExecutor.execute(runnable1);
		newSingleThreadExecutor.execute(runnable1);
		newSingleThreadExecutor.execute(runnable1);
		newSingleThreadExecutor.execute(runnable1);
		newSingleThreadExecutor.shutdown();//等任务结束之后才进行杀死----比较优雅
		
//		newSingleThreadExecutor.shutdownNow();//不管任务的死活直接杀死
		
		/*Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
					try {
						Thread.sleep(10000);
						System.out.println("--------------");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
		});
		thread.setDaemon(true);//默认是非守护线程 也就是不依托于主线程存在
		System.out.println(thread.isDaemon());
		thread.start();
		System.out.println("-------------------------main over");*/
		
	}

}
