package cn.itcast.heima2;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * 				�����������ģʽ ��ҵ���װ��һ��ҵ������
 * Description: ��ͳ���߳�ͨ������		���߳�10�� ���߳�100�� �������50��
 * Created on:  2016��3��24�� ����1:15:57 
 * @author bbaiggey
 * wait----->�߳����� ��ռ��CPU��Դ
 * sleep---->�߳�����ռ����CPU��Դ 
 */
public class TraditionalThreadCommunication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Business business = new Business();
		new Thread(
				new Runnable() {
					
					@Override
					public void run() {
					
						for(int i=1;i<=50;i++){
							business.sub(i);
						}
						
					}
				}
		).start();
		
		for(int i=1;i<=50;i++){
			business.main(i);
		}
		
	}

}
  class Business {
	  private boolean bShouldSub = true;
	  public synchronized void sub(int i){
		  while(!bShouldSub){
			  try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
			for(int j=1;j<=10;j++){
				System.out.println("sub thread sequence of " + j + ",loop of " + i);
			}
		  bShouldSub = false;
		  this.notify();
	  }
	  
	  public synchronized void main(int i){
		  	while(bShouldSub){
		  		try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  	}
			for(int j=1;j<=100;j++){
				System.out.println("main thread sequence of " + j + ",loop of " + i);
			}
			bShouldSub = true;
			this.notify();
	  }
  }
