package com.ibm.liuxhe.issue.problem3;

public class AddThread implements Runnable {

  private Method method;

  public AddThread(Method method) {
    this.method = method;
  }

  @Override
  public void run() {
    method.add();
  }

}
