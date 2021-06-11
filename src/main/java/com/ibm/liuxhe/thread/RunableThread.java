package com.ibm.liuxhe.thread;

// 创建线程的第二种模式
public class RunableThread implements Runnable {

  @Override
  public void run() {
    // Run
    for (int i = 0; i < 20; i++) {
      System.out.println("我在看代码---" + i);
    }
  }

  public static void main(String[] args) {
    // 创建runnable接口实现类对象
    RunableThread runableThread = new RunableThread();

    // 创建线程对象，通过线程对象来开启我们的线程代理
    Thread thread = new Thread(runableThread);

    thread.start();

    for (int i = 0; i < 2000; i++) {
      System.out.println("我在学习多线程---" + i);
    }

  }
}
