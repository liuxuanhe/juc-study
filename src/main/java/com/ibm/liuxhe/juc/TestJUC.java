package com.ibm.liuxhe.juc;

import java.util.concurrent.CopyOnWriteArrayList;

//测试JUC安全类的集合
public class TestJUC {

  public static void main(String[] args) throws InterruptedException {
    CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<String>();

    for (int i = 0; i < 1000; i++) {
      new Thread(() -> {
        copyOnWriteArrayList.add(Thread.currentThread().getName());
      }).start();
    }

    Thread.sleep(300);

    System.out.println(copyOnWriteArrayList.size());
  }
}
