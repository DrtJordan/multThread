package com.bg.thread;

/**
 * Created by Administrator on 2015/12/12.
 */
public class ReadMe {


    public static void main(String[] args) {
        new ReadMe().test();
    }

    private void test() {

       Thread thread= new Thread(new SayHi(),"th_hi");
        thread.start();



        try {
            Thread.sleep(1000);

            thread.interrupt();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    class SayHi implements Runnable{


        @Override
        public void run() {

            while (true) {

                ThreadLocal<String> local=new ThreadLocal<String>();
                local.set("hi");

                    //Thread.sleep(1000);

                    if (Thread.currentThread().isInterrupted()){
                        break;
                    }

                    System.out.println(Thread.currentThread().getName());
                    System.out.println(local.get());


            }

        }
    }
}
