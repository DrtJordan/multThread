package com.bg.异步调用;

/**
 * 
 * Description: TODO (用一句话描述该文件做什么，本句不是模版)
 * Copyright:   ©2015 Vbill Payment Co. Ltd. All rights reserved.
 * Created on:  2015年12月21日 下午6:37:45 
 * @author bai_ge@suixingpay.com
 */
public class Main {  
  public static void main(String[] args) {  
    System.out.println("main BEGIN");  
    Host host = new Host();  
    Data data1 = host.request(10, 'A');  
    Data data2 = host.request(20, 'B');  
    Data data3 = host.request(30, 'C');  
    System.out.println("main otherJob BEGIN");  
    try {  
      Thread.sleep(200);  
    } catch (InterruptedException e) {  
    }  
    System.out.println("main otherJob END");  
    System.out.println("data1	11111111 = " + data1.getContent());  
    System.out.println("data2 	22222222 = " + data2.getContent());  
    System.out.println("data3 	33333333 = " + data3.getContent());  
    System.out.println("main END");  
  }  
}

/**
main BEGIN
request(10, A) BEGIN
request(10, A) END
request(20, B) BEGIN
request(20, B) END
request(30, C) BEGIN
request(30, C) END
main otherJob BEGIN
making RealData(20, B) BEGIN
making RealData(30, C) BEGIN
making RealData(10, A) BEGIN
main otherJob END
making RealData(10, A) END
data1	11111111 = AAAAAAAAAA
making RealData(20, B) END
data2 	22222222 = BBBBBBBBBBBBBBBBBBBB
making RealData(30, C) END
data3 	33333333 = CCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
main END
 */
