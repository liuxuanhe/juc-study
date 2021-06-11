package com.ibm.liuxhe.issue.problem3;

public class Main {

  public static void main(String[] args) {
    Method method = new Method();

    for (int i = 0; i < 2; i++) {
      new Thread(new AddThread(method), "加法线程").start();
      new Thread(new ReduceThread(method), "减法线程").start();
    }
  }
}
