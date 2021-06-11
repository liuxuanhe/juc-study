package com.ibm.liuxhe.issue.problem3;

public class ReduceThread implements Runnable {

  private Method method;

  public ReduceThread(Method method) {
    this.method = method;
  }

  @Override
  public void run() {
    method.reduce();
  }
}
