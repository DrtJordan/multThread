package com.bg.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zenith on 2015/12/9.
 */
public class AtomicTest {

    private int num=0;
    private Object object=new Object();
    private Lock lock=new ReentrantLock();
    private AtomicInteger atomicNum=new AtomicInteger(0);


    public static void main(String[] args) throws InterruptedException {
        new AtomicTest().show();
    }

    private void show() throws InterruptedException {
        for (int i=0;i<5;i++){
            //new Thread(new Unsafe(),"unsafe_th"+i).start();
            new Thread(new SafeAtomic(),"safe_th"+i).start();
        }

    }

    class Unsafe implements  Runnable{

        public void run() {
            while( true ) {
                    if (num >= 10) {
                        //如果达到任务指标 完成某个任务
                        break;//中断线程执行
                    }
                    System.out.println(String.format("thread:%s   num:%s Do some thing", Thread.currentThread().getName(), num));
                    num++;
            }
        }
    }


    class SafeSynn implements  Runnable{

        public void run() {

            synchronized (object) {

                while (true) {
                    if (num >= 10) {
                        //如果达到任务指标 完成某个任务
                        break;//中断线程执行
                    }
                    System.out.println(String.format("thread:%s   num:%s Do some thing", Thread.currentThread().getName(), num));
                    num++;
                }
            }

        }
    }

    class SafeLock implements  Runnable{

        public void run() {
            lock.lock();

            while( true ) {
                if (num >= 10) {
                    //如果达到任务指标 完成某个任务
                    break;//中断线程执行
                }
                System.out.println(String.format("thread:%s   num:%s Do some thing", Thread.currentThread().getName(), num));
                num++;
            }

            lock.unlock();
        }
    }

    class SafeAtomic implements  Runnable{

        public void run() {
            while( true ) {
                if (atomicNum.get() >= 10) {
                    //如果达到任务指标 完成某个任务
                    break;//中断线程执行
                }
                System.out.println(String.format("thread:%s   num:%s Do some thing", Thread.currentThread().getName(), atomicNum.getAndIncrement()));
            }
        }
    }


}
