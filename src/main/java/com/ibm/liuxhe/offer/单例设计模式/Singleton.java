package com.ibm.liuxhe.offer.单例设计模式;

public class Singleton {

  private static class SingletonHodler {

    private static Singleton instance = new Singleton();
  }

  public static Singleton getInstance() {
    return SingletonHodler.instance;
  }

  private Singleton() {

  }

}
