package com.ibm.liuxhe.lock;

import java.util.concurrent.locks.LockSupport;
import org.junit.Test;

public class WaitNotify {

  public static Object baoZiDian = null;

  @Test
  public void waitNotifyTest() throws Exception {
    // 启动线程
    new Thread(() -> {
      if (baoZiDian == null) {
        synchronized (this) {
          try {
            System.out.println("1. 进入等待");
            this.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
      System.out.println("2. 买到包子");
    }).start();

    // 3秒之后，生产一个包子
    Thread.sleep(3000);
    baoZiDian = new Object();
    synchronized (this) {
      this.notifyAll();
      System.out.println("3. 通知消费者");
    }
  }

  @Test
  public void parkUnparkTest() throws Exception {
    // 定义线程
    Thread thread1 = new Thread(() -> {
      if (baoZiDian == null) {
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("1. 进入等待");
        LockSupport.park(); // 将当前线程挂起，等待继续执行的许可
      }
      System.out.println("2. 买到包子");
    });

    // 启动线程
    thread1.start();

    // 3秒之后，生产一个包子
    Thread.sleep(3000);
    baoZiDian = new Object();
    System.out.println("3. 通知消费者");
    LockSupport.unpark(thread1); // 给指定的线程，颁发继续运行的许可，（发信号）

    // 坚持
    Thread.sleep(10000);
  }
}
