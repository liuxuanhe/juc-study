package com.ibm.liuxhe.thread;

// 创建线程的第一种方式
public class MyThread extends Thread {

  @Override
  public void run() {
    // Run 
    for (int i = 0; i < 20; i++) {
      System.out.println("我在看代码---" + i);
    }
  }

  public static void main(String[] args) {
    MyThread myThread = new MyThread();

    myThread.start();

    for (int i = 0; i < 2000; i++) {
      System.out.println("我在学习多线程---" + i);
    }

  }

}
