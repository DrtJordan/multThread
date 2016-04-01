package cn.itcast.heima2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * 
 * Description: 信号灯--->[实现可用资源的统一管理]实现线程之间的互斥，与syn不同的是它自己获取的锁可以由其他线程释放--->构造方法中可以选择调度的原则可以实现公平调度
 * Created on:  2016年4月1日 下午4:06:31 
 * @author bbaiggey
 */
public class SemaphoreTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final  Semaphore sp = new Semaphore(3);
		for(int i=0;i<10;i++){
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						sp.acquire();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + 
							"进入，当前已有" + (3-sp.availablePermits()) + "个并发");
					try {
						Thread.sleep((long)(Math.random()*10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + 
							"即将离开");					
					sp.release();
					//下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
					System.out.println("线程" + Thread.currentThread().getName() + 
							"已离开，当前已有" + (3-sp.availablePermits()) + "个并发");					
				}
			};
			service.execute(runnable);			
		}
	}

}
