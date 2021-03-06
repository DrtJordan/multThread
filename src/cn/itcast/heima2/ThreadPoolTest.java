package cn.itcast.heima2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 
 * Description: 线程池[固定大小的、带有缓存的、单一的]
 * Created on:  2016年3月31日 下午5:23:20 
 * @author bbaiggey
 */
public class ThreadPoolTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		创建固定大小的线程池,当线程池中的线程用完之后就会等待有线程资源释放才能被执行.,发生异常就会终止,之后会创建新的线程出来
//		ExecutorService threadPool = Executors.newFixedThreadPool(3);		//线程池 数量是3
//		复用可用的线程,当超过60秒没有被使用就会被移除,池子中的线程不够时,会创建新的线程
//		ExecutorService threadPool = Executors.newCachedThreadPool();	//带缓存的线程池
		ExecutorService threadPool = Executors.newSingleThreadExecutor();	//单线程池--->线程死亡会自动启动一个新线程
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
		System.out.println("all of 10 tasks have committed! ");
//		threadPool.shutdown();//所有的都干完结束
//		threadPool.shutdownNow();//干完给定线程池的第一波就结束
		
//		创建一个指定数量的调度线程池				任务,第一次执行的间隔时间,多久重复一次，时间单位
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(//调度固定的频率
				new Runnable(){
					@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+"执行!");
				}},
				6,
				2,
				TimeUnit.SECONDS);
	}

}
