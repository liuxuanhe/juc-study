package com.ibm.liuxhe.issue.problem2;

//使用Lock和Condition实现子线程先循环10次、接着主线程循环20、再接着子线程循环10次、主线程循环20次、反复进行50次
public class Main {

  public static void main(String[] args) {

    Method method = new Method();

    new Thread(new ChildThread(method), "子线程").start();
    new Thread(new ParentThread(method), "父线程").start();
  }
}
