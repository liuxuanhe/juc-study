package com.ibm.liuxhe.issue.problem3web;

//设计4个线程、实现2个线程对i加一，2个线程对i进行减一
public class MutiThreadAdd {

  private static int i = 0;

  static class Add implements Runnable {

    @Override
    public synchronized void run() {
      i++;
      System.out.println(Thread.currentThread().getName() + "---" + i);
    }
  }

  static class Reduce implements Runnable {

    @Override
    public void run() {
      i--;
      System.out.println(Thread.currentThread().getName() + "---" + i);
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 2; i++) {
      new Thread(new Add(), "加法线程").start();
      new Thread(new Reduce(), "减法线程").start();
    }
  }
}
