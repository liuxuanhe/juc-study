package com.ibm.liuxhe.thread;

public class Race implements Runnable {

  private static String winner;

  @Override
  public void run() {

    for (int i = 0; i <= 100; i++) {

      // 模拟兔子睡觉
      if (Thread.currentThread().getName().equals("兔子") && i % 10 == 0) {
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      //如果比赛结束，就停止游戏
      boolean flag = gameOver(i);

      if (flag) {
        break;
      }

      System.out.println(Thread.currentThread().getName() + "跑了" + i + "步");
    }

  }


  private boolean gameOver(int steps) {
    if (winner != null) {
      // 如果已经存在胜利者
      return true;
    } else if (steps >= 100) {
      winner = Thread.currentThread().getName();
      System.out.println("Winner是" + winner);
      return true;
    }
    return false;
  }

  public static void main(String[] args) {

    Race race = new Race();

    new Thread(race, "兔子").start();
    new Thread(race, "乌龟").start();

  }
}
