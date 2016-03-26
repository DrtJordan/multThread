package cn.itcast.heima2;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 
 * Description: ��д��
 * Created on:  2016��3��26�� ����6:48:28 
 * @author bbaiggey
 */
/*ReentrantReadWriteLock��д����ʹ��
Lock�ȴ�ͳ�߳�ģ���е�synchronized��ʽ������������������е������ƣ�������ҲӦ����һ�����������߳�ִ�еĴ���Ƭ��Ҫʵ��ͬ�������Ч�������Ǳ�����ͬһ��Lock����

��д������Ϊ������д����������������⣬������д�����⣬������jvm�Լ����Ƶģ���ֻҪ�Ϻ���Ӧ�������ɡ������Ĵ���ֻ�����ݣ����Ժܶ���ͬʱ����������ͬʱд���Ǿ��϶����������Ĵ����޸����ݣ�ֻ����һ������д���Ҳ���ͬʱ��ȡ���Ǿ���д������֮������ʱ���϶�����д��ʱ����д����

ReentrantReadWriteLock��ʹ����������������⣬һ��������һ��д��
�߳̽��������ǰ��������
  û�������̵߳�д����
  û��д���������д���󣬵������̺߳ͳ��������߳���ͬһ��

�߳̽���д����ǰ��������
  û�������̵߳Ķ���
  û�������̵߳�д��

��ReentrantReadWriteLock������Ҫ��������ReentrantLock������ޡ����ͺ��߶��ǵ�����ʵ�֣��˴�֮��û�м̳л�ʵ�ֵĹ�ϵ��Ȼ������ܽ���������Ƶ������ˣ� 
   (a).���뷽�����ڲ���WriteLock���Ի�ȡReadLock�����Ƿ�����ReadLock��Ҫ���WriteLock����Զ����Ҫ�롣 
   (b).WriteLock���Խ���ΪReadLock��˳���ǣ��Ȼ��WriteLock�ٻ��ReadLock��Ȼ���ͷ�WriteLock����ʱ���߳̽�����Readlock�ĳ��С�������ReadLock��Ҫ����ΪWriteLock�򲻿��ܣ�Ϊʲô���ο�(a)���Ǻ�. 
   (c).ReadLock���Ա�����̳߳��в���������ʱ�ų��κε�WriteLock����WriteLock������ȫ�Ļ��⡣��һ������Ϊ��Ҫ����Ϊ���ڸ߶�ȡƵ�ʶ���Խϵ�д������ݽṹ��ʹ�ô�����ͬ�������������߲������� 
   (d).������ReadLock����WriteLock��֧��Interrupt��������ReentrantLockһ�¡� 
   (e).WriteLock֧��Condition������ReentrantLock����һ�£���ReadLock����ʹ��Condition�������׳�UnsupportedOperationException�쳣�� */
public class ReadWriteLockTest {
	public static void main(String[] args) {
		final Queue3 q3 = new Queue3();
		for(int i=0;i<3;i++)
		{
			new Thread(){
				public void run(){
					while(true){
						q3.get();						
					}
				}
				
			}.start();

			new Thread(){
				public void run(){
					while(true){
						q3.put(new Random().nextInt(10000));
					}
				}			
				
			}.start();
		}
		
	}
}

class Queue3{
	private Object data = null;//�������ݣ�ֻ����һ���߳���д�����ݣ��������ж���߳�ͬʱ�������ݡ�
	ReadWriteLock rwl = new ReentrantReadWriteLock();
//	��ȡ
	public void get(){
		rwl.readLock().lock();	//��ȡ����
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to read data!");
			Thread.sleep((long)(Math.random()*1000));
			System.out.println(Thread.currentThread().getName() + "have read data :" + data);			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.readLock().unlock();	//�ͷ���
		}
	}
//	д��
	public void put(Object data){

		rwl.writeLock().lock();	//��ȡд��
		try {
			System.out.println(Thread.currentThread().getName() + " be ready to write data!");					
			Thread.sleep((long)(Math.random()*1000));
			this.data = data;		
			System.out.println(Thread.currentThread().getName() + " have write data: " + data);					
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.writeLock().unlock();	//�ͷŶ���
		}
		
	
	}
}
