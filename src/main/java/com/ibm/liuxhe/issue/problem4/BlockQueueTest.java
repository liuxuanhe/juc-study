package com.ibm.liuxhe.issue.problem4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueueTest {

  //parseLog方法内部的代码不能改动
  public static void parseLog(String log) {
    System.out.println(log + ":" + (System.currentTimeMillis() / 1000));

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    //定义一个线程共享的队列容器，可以使得数据由队列的一端输入，从另外一端输出
    final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(16);
    //模拟生成日志
    for (int i = 0; i < 16; i++) {
      final String log = "" + (i + 1);
      try {
        queue.put(log);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }

    for (int i = 0; i < 4; i++) {
      new Thread(new Runnable() {
        boolean flag = true;

        @Override
        public void run() {
          while (flag) {
            try {
              parseLog(queue.take());
              if (queue.isEmpty()) {
                flag = false;
              }
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }).start();
    }
  }
}
