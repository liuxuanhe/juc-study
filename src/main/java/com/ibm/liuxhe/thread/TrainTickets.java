package com.ibm.liuxhe.thread;

//线程不安全
public class TrainTickets implements Runnable {

  // 火车票数量
  private int ticketNums = 10;

  @Override
  public void run() {
    while (true) {
      if (ticketNums <= 0) {
        break;
      }

      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println(Thread.currentThread() + " 买到了第：" + ticketNums-- + " 张票");
    }
  }

  public static void main(String[] args) {
    TrainTickets trainTickets = new TrainTickets();

    new Thread(trainTickets, "小明").start();
    new Thread(trainTickets, "小黑").start();
    new Thread(trainTickets, "黄牛").start();

  }
}
