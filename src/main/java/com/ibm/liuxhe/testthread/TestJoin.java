package com.ibm.liuxhe.testthread;

public class TestJoin implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
      System.out.println("线程VIp来了" + i);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    TestJoin testJoin = new TestJoin();
    Thread thread = new Thread(testJoin);
    thread.start();

    //主线程
    for (int i = 0; i < 500; i++) {
      System.out.println("main" + i);
      if (i == 200) {
        thread.join();//插队
      }
    }
  }
}
