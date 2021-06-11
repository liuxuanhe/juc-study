package com.ibm.liuxhe.issue.problem4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//现有的程序代码模拟产生了16个日志对象，并且需要运行16秒才能打印完这些日志
//请在程序中增加4个线程去调用parseLog()方法来分头打印这16个日志对象，程序只需要运行4秒即可打印完这些日志对象
public class BlockQueue {

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

    final boolean flag = true;

    //模拟生成日志
    for (int i = 0; i < 16; i++) {
      final String log = "" + (i + 1);
      {
        try {
          queue.put(log);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    BlockQueue blockQueue = new BlockQueue();

    for (int i = 0; i < 4; i++) {
      new Thread(new MutiThread(blockQueue, queue)).start();
    }
  }

}

class MutiThread implements Runnable {

  private BlockQueue blockQueue;
  private BlockingQueue<String> queue;

  public MutiThread(BlockQueue blockQueue, BlockingQueue<String> queue) {
    this.blockQueue = blockQueue;
    this.queue = queue;
  }

  private boolean flag = true;

  @Override
  public void run() {
    while (flag) {
      try {
        blockQueue.parseLog(queue.take());

        if (queue.isEmpty()) {
          flag = false;
        }

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
