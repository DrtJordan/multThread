package com.bg.thread;

/**
 * Created by Administrator on 2015/12/12.
 */
public class CreateThread {


    private  Object object=new Object();
    public static void main(String[] args) {

        new CreateThread().test();
       // new CreateThread().hello();
    }

    private void test(){

        //创建
        Thread thHello=new SayHello();
        thHello.setName("th_hello");

        SayHi sayHi=new SayHi();
        Thread thHi1 =new Thread(sayHi,"th_hi1");
        //Thread thHi2 =new Thread(sayHi,"th_hi2");

        //启动
        thHello.start();
        thHi1.start();
        try {


            thHi1.join();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // thHi2.start();

        System.out.println(Thread.currentThread().getName());

    }


    // 继承
    class  SayHello extends Thread{


        @Override
        public void run() {

//            try {
//                sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }


         //   synchronized (object) {


                    System.out.println(this.getName());
                    System.out.println("hello");

               //     object.notify();




            //}
        }
    }

    //实现
    class SayHi implements Runnable{


        @Override
        public void run() {
            //synchronized (object) {

            try {


                     Thread.sleep(1000);
                    //object.wait();
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("hi");




            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //}
        }
    }

    private synchronized void hello(){


    }

}
