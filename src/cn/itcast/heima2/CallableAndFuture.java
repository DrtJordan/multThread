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
 * 
 * Description: CallableAndFuture		---->	���ֻ�ȡ�߳�ִ��֮�󷵻ؽ���ķ�ʽ  ��ʵ���ǻ�ȡFuture ����get()����
 * ExecutorService		------->
 * ExecutorCompletionService		----->�߳�
 * Created on:  2016��3��25�� ����9:15:47 
 * @author bbaiggey
 */

public class CallableAndFuture {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService threadPool =  Executors.newSingleThreadExecutor();
		Future<String> future =
			threadPool.submit(
				new Callable<String>() {
					public String call() throws Exception {
						Thread.sleep(3000);
						return "hello";
					};
				}
		);
		System.out.println("�ȴ����");
	
		try {
			System.out.println("�õ������" + future.get());
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
//		�ر�
		threadPool.shutdown();
		threadPool2.shutdown();
	}
	
	

}
