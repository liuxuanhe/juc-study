package com.ibm.liuxhe.testthread;

// 测试stop
// 1. 建议线程正常停止 -> 利用次数，不建议死循环
//2. 建议使用标志为 -> 设置一个标志位
//3.不要使用stop和 destroy等jdk过时的方法
public class TestThreadStop implements Runnable {

  // 1. 设置一个标志位
  private boolean flag = true;

  @Override
  public void run() {
    int i = 0;
    while (flag) {
      System.out.println("Run Thread---" + i++);
    }
  }

  public void stop() {
    this.flag = false;
  }

  public static void main(String[] args) {
    TestThreadStop testThreadStop = new TestThreadStop();
    new Thread(testThreadStop).start();

    for (int i = 0; i < 1000; i++) {
      System.out.println("Main--" + i);
      if (i == 900) {
        //切换标志位，让线程停止
        testThreadStop.stop();
        System.out.println("线程停止了");
      }
    }
  }
}
