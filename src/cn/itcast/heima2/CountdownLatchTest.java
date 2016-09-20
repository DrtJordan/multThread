package cn.itcast.heima2;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * Description: 一组线程执行一组命令				只有全部的任务执行结束整个进程才结束 任务才算完成
 * Created on:  2016年3月30日 下午4:29:43 
 * @author bbaiggey
 */
public class CountdownLatchTest {

	public static void main(String[] args) {
//		创建线程池
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);
		final CountDownLatch cdAnswer = new CountDownLatch(3);		
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						System.out.println("线程" + Thread.currentThread().getName() + "正准备接受命令");						
						cdOrder.await();
						System.out.println("线程" + Thread.currentThread().getName() + "已接受命令");								
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("线程" + Thread.currentThread().getName() + "回应命令处理结果");						
						cdAnswer.countDown();						
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}
			};
			service.execute(runnable);
		}		
		try {
			Thread.sleep((long)(Math.random()*10000));
		
			System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令");						
			cdOrder.countDown();
			System.out.println("线程" + Thread.currentThread().getName() + "已发送命令，正在等待结果");	
			cdAnswer.await();
			System.out.println("线程" + Thread.currentThread().getName() + "已收到所有响应结果");	
		} catch (Exception e) {
			e.printStackTrace();
		}				
		service.shutdown();

	}
}
