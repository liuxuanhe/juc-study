package com.ibm.liuxhe.testthread;

//测试守护进程
public class TestDaemon {

  public static void main(String[] args) {
    God god = new God();
    You you = new You();

    Thread thread = new Thread(god);
    thread.setDaemon(true); //默认线程都是用户线程，只有true才是守护线程
    thread.start();

    // 用户线程
    new Thread(you).start();

  }
}


// 大
class God implements Runnable {

  @Override
  public void run() {
    while (true) {
      System.out.println("守护线程");
    }
  }
}

//你
class You implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 36500; i++) {
      System.out.println("你一生都开心的活着");
    }
    System.out.println("Good Bye");
  }
}