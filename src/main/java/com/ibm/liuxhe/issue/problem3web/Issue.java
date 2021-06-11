package com.ibm.liuxhe.issue.problem3web;

public class Issue {

  private int i = 0;

  private synchronized void add() {
    i++;
    System.out.println(Thread.currentThread().getName() + "加" + i);
  }

  private synchronized void sub() {
    i--;
    System.out.println(Thread.currentThread().getName() + "减" + i);
  }

  //实现内部类
  class First implements Runnable {

    public void run() {
      add();
    }
  }

  //实现内部类
  class Second implements Runnable {

    public void run() {
      sub();
    }
  }

  public static void main(String[] args) {
    Issue issue = new Issue();
    First f = issue.new First();
    Second s = issue.new Second();

    for (int i = 0; i < 2; i++) {
      Thread t = new Thread(f);
      t.start();
      Thread t1 = new Thread(s);
      t1.start();
    }
  }
}
