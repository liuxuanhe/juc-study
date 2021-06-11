package com.ibm.liuxhe.lock;

import java.util.concurrent.locks.Lock;

public class DemoLock {

  int i = 0;

  public static void main(String[] args) {
    DemoLock demoLock = new DemoLock();

    Lock lock = new NeteaseLock();

    Thread[] threads = new Thread[7];

    for (int i = 0; i < threads.length; i++) {
      int finalI = i;
      Thread thread = new Thread(() -> {
        System.out.println("线程：" + finalI + " 开始执行");

        for (int j = 0; j < 10000; j++) {
          lock.lock();
          try {
            // 执行
            demoLock.i++;
          } finally {
            // 执行完 释放锁
            lock.unlock();
          }
        }

        System.out.println("线程：" + finalI + " 执行完毕");
      });

      threads[i] = thread;
      thread.start();
    }

    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println(demoLock.i);
  }
}
