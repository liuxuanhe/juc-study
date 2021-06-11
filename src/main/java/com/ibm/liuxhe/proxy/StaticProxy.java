package com.ibm.liuxhe.proxy;

// 静态代理模式总结
// 真实对象和代理对象都要实现同一个接口
// 代理对象要代理真实角色

//好处：
// 代理对象可以做很多真实对象做不了的事情
// 真实对象专注做自己的事情
public class StaticProxy {

  public static void main(String[] args) {

    You you = new You(); //你要结婚

    // 代理模式
    new Thread(() -> {
      System.out.println("aaaaa");
    }).start();

    new WeddingCompany(new You()).HappyMarry();

    // 代理模式
    WeddingCompany weddingCompany = new WeddingCompany(you);
    weddingCompany.HappyMarry();

  }
}

interface Marry {

  void HappyMarry();
}

// 真实角色，你去结婚
class You implements Marry {

  @Override
  public void HappyMarry() {
    System.out.println("结婚了");
  }
}

// 代理角色，帮助你结婚
class WeddingCompany implements Marry {

  // 代理真实目标角色
  private Marry traget;

  public WeddingCompany(Marry traget) {
    this.traget = traget;
  }

  @Override
  public void HappyMarry() {
    before();
    this.traget.HappyMarry(); //这是真实对象
    after();
  }

  private void before() {
    System.out.println("结婚之前，布置现场");
  }

  private void after() {
    System.out.println("结婚之后，收尾款");
  }

}