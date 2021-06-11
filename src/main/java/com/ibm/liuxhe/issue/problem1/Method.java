package com.ibm.liuxhe.issue.problem1;

public class Method {

  private static boolean flag = true;

  public synchronized void childMethod() {

    while (!flag) {
      try {
        //如果执行完10次，则等待父线程执行
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    for (int i = 0; i < 10; i++) {
      System.out.println(Thread.currentThread().getName() + "---执行---" + i + "次");
    }

    flag = false;
    this.notifyAll();

  }

  public synchronized void parentMethod() {

    while (flag) {
      //如果执行完20次，则等待子线程执行
      try {
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    for (int i = 0; i < 20; i++) {
      System.out.println(Thread.currentThread().getName() + "---执行---" + i + "次");
    }

    flag = true;
    this.notifyAll();
  }

}
