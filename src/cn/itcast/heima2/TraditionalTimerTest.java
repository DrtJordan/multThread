package cn.itcast.heima2;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 
 * Description: 传统的定时器
 * Created on:  2016年3月23日 下午11:00:18 
 * @author bbaiggey
 */
public class TraditionalTimerTest {

	private static int count = 0;
	public static void main(String[] args) {
/*		new Timer().schedule(new TimerTask() {//每隔3秒执行一次
			
			@Override
			public void run() {
				System.out.println("bombing!");
				
			}
		}, 10000,3000);*/
		
//每隔2秒 4秒交替执行代码
		class MyTimerTask extends TimerTask{
			
			@Override
			public void run() {
				count = (count+1)%2;
				System.out.println("bombing!");
				new Timer().schedule(/*new TimerTask() {
					
					@Override
					public void run() {
						System.out.println("bombing!");
					}
				}*/new MyTimerTask(),2000+2000*count);
			}
		}
		
		new Timer().schedule(new MyTimerTask(), 2000);
		
		while(true){
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
