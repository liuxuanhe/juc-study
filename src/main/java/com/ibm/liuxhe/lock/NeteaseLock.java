package com.ibm.liuxhe.lock;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class NeteaseLock implements Lock {

  // 需要有一个标志，来判断是否有人拿到锁
  AtomicReference<Thread> owner = new AtomicReference<>();

  // 集合 存储我们等待线程的信息
  LinkedBlockingDeque<Thread> waiters = new LinkedBlockingDeque<>();

  @Override
  public boolean tryLock() {
    // CAS 修改（内存操作)
    return owner.compareAndSet(null, Thread.currentThread());
  }


  @Override
  public void lock() {
    // 第一次先尝试获取锁
    boolean park = false;

    while (!tryLock()) {
      if (!park) {
        // 加入等待集合
        waiters.offer(Thread.currentThread());
        park = true;
      } else {
        // 进入等待许可
        LockSupport.park();
      }
    }

    // 拿到锁的线程，移除出队列
    waiters.remove(Thread.currentThread());
  }

  @Override
  public void lockInterruptibly() throws InterruptedException {

  }

  @Override
  public void unlock() {
    // 释放锁
    if (owner.compareAndSet(Thread.currentThread(), null)) {
      // 遍历等待者，通知继续执行
      Thread next = null;
      while ((next = waiters.peek()) != null) {
        LockSupport.unpark(next);
      }
    }
  }

  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    return false;
  }


  @Override
  public Condition newCondition() {
    return null;
  }
}
