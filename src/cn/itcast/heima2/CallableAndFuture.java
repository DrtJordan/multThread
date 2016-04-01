package cn.itcast.heima2;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 	Future获得结果类型和Callable返回的结果类型必须一致，这是通过泛型来实现的。 Callable要采用ExecutorService的submit方法提交，返回future对象可以取消任务
 * CompletionService用于提交一组Callable任务，其take方法返回已完成的一个Callable任务对应的Future对象	同事提交任务 哪个县执行完就先拿到结果
 * Description: CallableAndFuture		---->	两种获取线程执行之后返回结果的方式  其实都是获取Future 调用get()方法  //有返回值使用submit 不需要返回值使用execute
 * ExecutorService		------->
 * ExecutorCompletionService		----->线程
 * Created on:  2016年3月25日 下午9:15:47 
 * @author bbaiggey
 */

public class CallableAndFuture {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService threadPool =  Executors.newSingleThreadExecutor();
		Future<String> future =
			threadPool.submit(	//有返回值使用submit 不需要返回值使用execute
				new Callable<String>() {
					public String call() throws Exception {
						Thread.sleep(3000);
						return "hello";
					};
				}
		);
		System.out.println("等待结果");
	
		try {
			System.out.println("拿到结果：" + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ExecutorService threadPool2 =  Executors.newFixedThreadPool(10);
		
		/** Creates an ExecutorCompletionService using the supplied
	     * executor for base task execution and a
	     * {@link LinkedBlockingQueue} as a completion queue.
	     *
	     * @param executor the executor to use
	     * @throws NullPointerException if executor is {@code null}*/
//		同事提交任务 哪个县执行完就先拿到结果
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
		for(int i=1;i<=10;i++){
			final int seq = i;
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(3000));
					return seq;
				}
			});
		}
		for(int i=0;i<10;i++){
			try {
				System.out.println(
						completionService.take().get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		关闭
		threadPool.shutdown();
		threadPool2.shutdown();
	}
	
	

}
