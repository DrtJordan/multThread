package com.bg.thread;

import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2015/12/12.
 */
public class ThreadPool {

    public static void main(String[] args) {
        new ThreadPool().test();
    }

    private void test() {

        //创建池子
        //提交任务
        //关闭资源

        //单线程的
      ExecutorService service= Executors.newSingleThreadExecutor();

       // service.submit(new SayHello());

        Future<String> future=  service.submit(new ShowHello());
        try {



            System.out.println(future.get());



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        finally {
            service.shutdown();
        }



        /** 固定数目线程
        ExecutorService service =Executors.newFixedThreadPool(2);
        service.submit(new SayHello());
        service.submit(new SayHello());
        service.submit(new SayHello());
        service.shutdown();
        **/

        /**动态可变数目的线程池
        ExecutorService service =Executors.newCachedThreadPool();
        service.submit(new SayHello());
        service.submit(new SayHello());
        service.submit(new SayHello());
        service.submit(new SayHello());
        service.shutdown();
         **/
        /**
        ScheduledExecutorService service =Executors.newScheduledThreadPool(2);

        /**简单的延迟执行
        service.schedule(new SayHello(),1, TimeUnit.SECONDS);
        System.out.println(new Date());
        service.shutdown();
        **/

        /**FixedDelay
        service.scheduleWithFixedDelay(new SayHello(),1,1,TimeUnit.SECONDS);
        System.out.println(new Date());
         **/
        /**
         service.scheduleAtFixedRate(new SayHello(),1,3,TimeUnit.SECONDS);
         System.out.println(new Date());
        **/
    }


    class SayHello implements Runnable{


        @Override
        public void run() {

            try {
                Thread.sleep(2000);
                System.out.println(String.format("hello--%s %s",Thread.currentThread().getName(),new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class  ShowHello implements Callable{

        @Override
        public String call() throws Exception {
            return "Hello";
        }
    }


}
