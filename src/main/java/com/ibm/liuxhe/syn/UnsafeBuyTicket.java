package com.ibm.liuxhe.syn;

// 不安全的买票
public class UnsafeBuyTicket {

  public static void main(String[] args) {
    BuyTicket buyTicket = new BuyTicket();

    new Thread(buyTicket, "我").start();
    new Thread(buyTicket, "黄牛").start();
    new Thread(buyTicket, "小明").start();

  }
}

class BuyTicket implements Runnable {

  //票
  int ticketNums = 10;
  boolean flag = true; //外部停止

  @Override
  public void run() {
    // 买票
    while (flag) {
      buy();
    }
  }

  // 同步方法
  // 锁的是this
  private synchronized void buy() {
    if (ticketNums <= 0) {
      flag = false;
      return;
    }

    //模拟延时
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(Thread.currentThread().getName() + "拿到" + ticketNums--);
  }
}