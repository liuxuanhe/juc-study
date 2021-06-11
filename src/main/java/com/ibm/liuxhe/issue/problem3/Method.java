package com.ibm.liuxhe.issue.problem3;

public class Method {

  private int i = 0;

  public synchronized void add() {
    i++;
    System.out.println(Thread.currentThread().getName() + "---" + i);
  }

  public synchronized void reduce() {
    i--;
    System.out.println(Thread.currentThread().getName() + "---" + i);
  }
}
