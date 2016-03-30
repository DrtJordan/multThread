package cn.itcast.heima2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
/**
 * 
 * Description: һ��ͬ�������࣬�����һ�����������߳���ִ�еĲ���֮ǰ��������һ�������߳�һֱ�ȴ���
 * ���췽������ָ���˼����Ĵ���countDown��������ǰ�̵߳��ô˷������������һ	awaint���������ô˷�����һֱ������ǰ�̣߳�ֱ����ʱ����ֵΪ0
 * Created on:  2016��3��30�� ����5:14:27 
 * @author bbaiggey
 */
public class CountDownLatchDemo {
	final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) throws InterruptedException {
    	CountDownLatch latch=new CountDownLatch(2);//�������˵�Э��
    	Worker worker1=new Worker("zhang san", 5000, latch);
    	Worker worker2=new Worker("li si", 8000, latch);
    	worker1.start();//
    	worker2.start();//
    	latch.await();//�ȴ����й�����ɹ���
        System.out.println("all work done at "+sdf.format(new Date()));
	}
    
    
    static class Worker extends Thread{
    	String workerName; 
    	int workTime;
    	CountDownLatch latch;
    	public Worker(String workerName ,int workTime ,CountDownLatch latch){
    		 this.workerName=workerName;
    		 this.workTime=workTime;
    		 this.latch=latch;
    	}
    	public void run(){
    		System.out.println("Worker "+workerName+" do work begin at "+sdf.format(new Date()));
    		doWork();//������
    		System.out.println("Worker "+workerName+" do work complete at "+sdf.format(new Date()));
    		latch.countDown();//������ɹ�������������һ

    	}
    	
    	private void doWork(){
    		try {
				Thread.sleep(workTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
    
     
}
