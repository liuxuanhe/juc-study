package com.ibm.liuxhe.overall;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class OverAll {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    // 1.
    ExtendsThread extendsThread = new ExtendsThread();
    extendsThread.start();

    // 2.
    new Thread(new ImplRunnable()).start();

    // 3.
    FutureTask<Integer> futureTask = new FutureTask<Integer>(new ImplCallable());
    new Thread(futureTask).start();

    Integer a = futureTask.get();
    System.out.println("aaa: " + a);

    // 4.
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    Future<Integer> future = executorService.submit(new ImplCallable());
    Integer b = future.get();
    System.out.println("bbb: " + b);

    // 5.
    executorService.submit(new ImplRunnable());

    // 关闭线程池
    executorService.shutdown();
  }
}

class ExtendsThread extends Thread {

  @Override
  public void run() {
    System.out.println("MyThread - Extends - Thread");
  }
}


class ImplRunnable implements Runnable {

  @Override
  public void run() {
    System.out.println("ImplRunnable - implements - Runnable");
  }
}

class ImplCallable implements Callable<Integer> {

  int i = 111;

  @Override
  public Integer call() throws Exception {
    System.out.println("ImplCallable - implements - Callable");
    return i++;
  }
}