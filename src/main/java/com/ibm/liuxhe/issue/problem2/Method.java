package com.ibm.liuxhe.issue.problem2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Method {

  private boolean flag = false;
  Lock lock = new ReentrantLock();
  Condition condition = lock.newCondition();

  public void childMethod() {

    try {
      lock.lock();

      while (flag) {
        try {
          condition.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      for (int i = 0; i < 10; i++) {
        System.out.println(Thread.currentThread().getName() + "---执行---" + i + "次");
      }

      flag = true;
      condition.signal();
    } finally {
      lock.unlock();
    }
  }

  public void parentMethod() {
    try {
      lock.lock();

      while (!flag) {
        try {
          condition.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      for (int i = 0; i < 20; i++) {
        System.out.println(Thread.currentThread().getName() + "---执行---" + i + "次");
      }

      flag = false;
      condition.signal();
    } finally {
      lock.unlock();
    }
  }
}
