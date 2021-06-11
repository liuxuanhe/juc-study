package com.ibm.liuxhe.issue.problem2;

public class ChildThread implements Runnable {

  private Method method;

  public ChildThread(Method method) {
    this.method = method;
  }

  @Override
  public void run() {
    for (int i = 0; i < 50; i++) {
      method.childMethod();
    }
  }
}
