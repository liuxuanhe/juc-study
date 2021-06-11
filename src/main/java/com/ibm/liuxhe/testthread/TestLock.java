package com.ibm.liuxhe.testthread;

import java.util.concurrent.locks.ReentrantLock;

// 测试Lock锁
public class TestLock {

  public static void main(String[] args) {
    TestLock2 testLock2 = new TestLock2();

    new Thread(testLock2).start();
    new Thread(testLock2).start();
    new Thread(testLock2).start();
  }

}

class TestLock2 implements Runnable {

  int ticketNums = 10;

  boolean flag = true;

  //定义锁
  private final ReentrantLock lock = new ReentrantLock();

  @Override
  public void run() {
    while (flag) {
      lock.lock();
      try {
        if (ticketNums > 0) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(ticketNums--);
        } else {
          flag = false;
        }
      } finally {
        //解锁
        lock.unlock();
      }

    }
  }
}