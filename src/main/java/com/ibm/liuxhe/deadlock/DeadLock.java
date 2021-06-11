package com.ibm.liuxhe.deadlock;

// 死锁：多个线程互相抱着对方需要的资源，然后形成死锁
public class DeadLock {

  public static void main(String[] args) {
    MakeUp girl1 = new MakeUp(0, "小明");
    MakeUp girl2 = new MakeUp(1, "小黄");

    girl1.start();
    girl2.start();
  }
}

//口红
class Lipstick {

  public Lipstick() {
    System.out.println("创建一张口红");
  }
}

//镜子
class Mirror {

  public Mirror() {
    System.out.println("创建一张镜子");
  }
}

class MakeUp extends Thread {

  //需要的资源只有一份，用static来保证只有一份
  static Lipstick lipstick = new Lipstick();
  static Mirror mirror = new Mirror();

  int choice; //选择
  String girlName; //使用化妆品的人

  MakeUp(int choice, String girlName) {
    this.choice = choice;
    this.girlName = girlName;
  }

  @Override
  public void run() {
    // 化妆
    try {
//      makeup();
      makeupSafe();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  //化妆，互相持有对方的锁，需要拿到对方的资源
  private void makeup() throws InterruptedException {
    if (choice == 0) {
      synchronized (lipstick) {
        //获得口红的锁
        System.out.println(this.girlName + "获得口红的锁");
        Thread.sleep(1000);

        synchronized (mirror) {
          //获得镜子的锁
          System.out.println(this.girlName + "获得镜子的锁");
        }
      }
    } else {
      synchronized (mirror) {
        //获得口红的锁
        System.out.println(this.girlName + "获得镜子的锁");
        Thread.sleep(2000);

        synchronized (lipstick) {
          //获得镜子的锁
          System.out.println(this.girlName + "获得口红的锁");
        }
      }
    }
  }

  //化妆，互相持有对方的锁，需要拿到对方的资源
  private void makeupSafe() throws InterruptedException {
    if (choice == 0) {
      synchronized (lipstick) {
        //获得口红的锁
        System.out.println(this.girlName + "获得口红的锁");
        Thread.sleep(1000);
      }

      synchronized (mirror) {
        //获得镜子的锁
        System.out.println(this.girlName + "获得镜子的锁");
      }
    } else {
      synchronized (mirror) {
        //获得口红的锁
        System.out.println(this.girlName + "获得镜子的锁");
        Thread.sleep(2000);
      }

      synchronized (lipstick) {
        //获得镜子的锁
        System.out.println(this.girlName + "获得口红的锁");
      }
    }
  }
}
