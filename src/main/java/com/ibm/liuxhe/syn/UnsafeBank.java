package com.ibm.liuxhe.syn;

// 不安全的取钱
// 两个人取钱
public class UnsafeBank {

  public static void main(String[] args) {
    //账户
    Account account = new Account(1000, "基金");

    Drawing you = new Drawing(account, 50, "你");
    Drawing me = new Drawing(account, 100, "我");

    you.start();
    me.start();
  }

}


//账户
class Account {

  int money; //余额
  String name; //卡名

  public Account(int money, String name) {
    this.money = money;
    this.name = name;
  }
}

// 银行
class Drawing extends Thread {

  Account account;

  // 取了多少钱
  int drawingMoney;

  // 现在手里的钱
  int nowMoney;

  public Drawing(Account account, int drawingMoney, String name) {
    super(name);
    this.account = account;
    this.drawingMoney = drawingMoney;
  }

  @Override
  public void run() {

    //锁的对象 应该是 变化的量， 需要增删改的对象
    synchronized (account) {

      //判断余额
      if (account.money - drawingMoney <= 0) {
        System.out.println("钱不够，取不了");
        return;
      }

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      //卡内余额 = 余额-取钱
      account.money = account.money - drawingMoney;

      // 你手里的现金
      nowMoney = nowMoney + drawingMoney;

      System.out.println(account.name + "余额为：" + account.money);
      System.out.println(this.getName() + "手里的钱" + nowMoney);
    }
  }
}
