package cn.itcast.heima2;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 
 * Description: ��ͳ�Ķ�ʱ��
 * Created on:  2016��3��23�� ����11:00:18 
 * @author bbaiggey
 */
public class TraditionalTimerTest {

	private static int count = 0;
	public static void main(String[] args) {
/*		new Timer().schedule(new TimerTask() {//ÿ��3��ִ��һ��
			
			@Override
			public void run() {
				System.out.println("bombing!");
				
			}
		}, 10000,3000);*/
		
//ÿ��2�� 4�뽻��ִ�д���
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
