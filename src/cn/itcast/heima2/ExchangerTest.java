package cn.itcast.heima2;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * Description: 两个线程之间进行数据交换  exchanger.exchange(data1);
 * Created on:  2016年3月31日 下午3:15:05 
 * @author bbaiggey
 */
public class ExchangerTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		@SuppressWarnings("rawtypes")
		final Exchanger exchanger = new Exchanger();
		service.execute(new Runnable(){
			public void run() {
				try {				
					String data1 = "zxx";
					System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data1 +"换出去");
					Thread.sleep((long)(Math.random()*10000));
					String data2 = (String)exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为" + data2);
				}catch(Exception e){
					
				}
			}	
		});
		service.execute(new Runnable(){
			public void run() {
				try {				

					String data1 = "lhm";
					System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data1 +"换出去");
					Thread.sleep((long)(Math.random()*10000));					
					String data2 = (String)exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为" + data2);
				}catch(Exception e){
					
				}				
			}	
		});		
		service.shutdown();
	}
}
