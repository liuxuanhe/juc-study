package com.ibm.liuxhe.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// 创建线程的方式三： 实现Callable接口
public class CallableThread implements Callable<Boolean> {

  private String url; // 网络图片地址
  private String name; // 保存的文件名

  public CallableThread(String url, String name) {
    this.url = url;
    this.name = name;
  }

  @Override
  public Boolean call() {
    WebDownloader webDownloader = new WebDownloader();
    webDownloader.downloader(url, name);
    System.out.println("下载了文件名：" + name);
    return true;
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    CallableThread downloadPicThread1 = new CallableThread(
        "https://www.baidu.com/s?rsv_idx=1&wd=Docker&fenlei=256&usm=1&ie=utf-8&rsv_cq=&rsv_dl=0_right_recommends_merge_21102&rsv_pq=baab1d32000cc23a&oq=marven&rsv_t=6e4dp7mBa74%2B5lgwx0U7XdXjBI4liZDQHQpq77Pe7bu8uJq7D%2FFBRBmulZc&euri=af63f130cd0b450ca74ed753a8870973",
        "docker.jpg");
    CallableThread downloadPicThread2 = new CallableThread(
        "https://www.baidu.com/s?rsv_idx=1&wd=linux&fenlei=256&usm=1&ie=utf-8&rsv_cq=&rsv_dl=0_right_recommends_merge_21102&rsv_pq=baab1d32000cc23a&oq=marven&rsv_t=4bd4P%2FptdioSnK7qbu4wZghN1Ss66hZZP1PamKYc5ywRhucAcOagC8f9luc&euri=bbb53ce6caa348c2b773cdd71d3265bc",
        "linux.jpg");
    CallableThread downloadPicThread3 = new CallableThread(
        "https://www.baidu.com/s?rsv_idx=1&wd=nginx&fenlei=256&usm=1&ie=utf-8&rsv_cq=&rsv_dl=0_right_recommends_merge_21102&rsv_pq=baab1d32000cc23a&oq=marven&rsv_t=7070LR1tE3tUJ7AaQO8Va50%2BNEhh327GKDbWEa4KrPoCbz%2Fv9Xxpyitx6xU&euri=1771e9d29f0e48b1ae808b9ab296bce5",
        "nginx.jpg");

    // 创建执行服务
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    // 提交执行
    Future<Boolean> r1 = executorService.submit(downloadPicThread1);
    Future<Boolean> r2 = executorService.submit(downloadPicThread2);
    Future<Boolean> r3 = executorService.submit(downloadPicThread3);

    // 获取结果
    boolean rs1 = r1.get();
    boolean rs2 = r2.get();
    boolean rs3 = r3.get();

    // 关闭服务
    executorService.shutdownNow();
  }
}
