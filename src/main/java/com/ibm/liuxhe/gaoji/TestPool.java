package com.ibm.liuxhe.gaoji;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestPool {

  public static void main(String[] args) {
    // 1. 创建服务，创建线程池
    // 参数为线程池大小
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    // 执行
    executorService.execute(new MyThread());
    executorService.execute(new MyThread());
    executorService.execute(new MyThread());
    executorService.execute(new MyThread());
    executorService.execute(new MyThread());

    // 2. 关闭连接
    executorService.shutdown();
  }
}

class MyThread implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      System.out.println(Thread.currentThread().getName() + " " + i);
    }
  }
}