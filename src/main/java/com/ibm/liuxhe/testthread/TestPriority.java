package com.ibm.liuxhe.testthread;

// 测试线程的优先级
// CPU调度的时候优先级高的调度概率高
// 不是一定会先执行
public class TestPriority {

  //
  public static void main(String[] args) {
    // 主线程默认优先级
    System.out
        .println(Thread.currentThread().getName() + "----" + Thread.currentThread().getPriority());

    MyPriority myPriority = new MyPriority();

    Thread t1 = new Thread(myPriority);
    Thread t2 = new Thread(myPriority);
    Thread t3 = new Thread(myPriority);
    Thread t4 = new Thread(myPriority);
    Thread t5 = new Thread(myPriority);
    Thread t6 = new Thread(myPriority);

    // 先设置优先级，再启动
    t1.start();

    // 2
    t2.setPriority(1);
    t2.start();

    // 3
    t3.setPriority(4);
    t3.start();

    // 最大
    t4.setPriority(Thread.MAX_PRIORITY);
    t4.start();

//    //
//    t5.setPriority(-1);
//    t5.start();
//
//    //
//    t6.setPriority(11);
//    t6.start();
  }
}


class MyPriority implements Runnable {

  @Override
  public void run() {
    System.out
        .println(Thread.currentThread().getName() + "----" + Thread.currentThread().getPriority());
  }
}