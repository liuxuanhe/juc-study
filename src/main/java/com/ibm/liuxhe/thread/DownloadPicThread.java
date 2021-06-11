package com.ibm.liuxhe.thread;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;

public class DownloadPicThread extends Thread {

  private String url; // 网络图片地址
  private String name; // 保存的文件名

  public DownloadPicThread(String url, String name) {
    this.url = url;
    this.name = name;
  }

  @Override
  public void run() {
    WebDownloader webDownloader = new WebDownloader();
    webDownloader.downloader(url, name);
    System.out.println("下载了文件名：" + name);
  }

  public static void main(String[] args) {
    DownloadPicThread downloadPicThread1 = new DownloadPicThread(
        "https://www.baidu.com/s?rsv_idx=1&wd=Docker&fenlei=256&usm=1&ie=utf-8&rsv_cq=&rsv_dl=0_right_recommends_merge_21102&rsv_pq=baab1d32000cc23a&oq=marven&rsv_t=6e4dp7mBa74%2B5lgwx0U7XdXjBI4liZDQHQpq77Pe7bu8uJq7D%2FFBRBmulZc&euri=af63f130cd0b450ca74ed753a8870973",
        "docker.jpg");
    DownloadPicThread downloadPicThread2 = new DownloadPicThread(
        "https://www.baidu.com/s?rsv_idx=1&wd=linux&fenlei=256&usm=1&ie=utf-8&rsv_cq=&rsv_dl=0_right_recommends_merge_21102&rsv_pq=baab1d32000cc23a&oq=marven&rsv_t=4bd4P%2FptdioSnK7qbu4wZghN1Ss66hZZP1PamKYc5ywRhucAcOagC8f9luc&euri=bbb53ce6caa348c2b773cdd71d3265bc",
        "linux.jpg");
    DownloadPicThread downloadPicThread3 = new DownloadPicThread(
        "https://www.baidu.com/s?rsv_idx=1&wd=nginx&fenlei=256&usm=1&ie=utf-8&rsv_cq=&rsv_dl=0_right_recommends_merge_21102&rsv_pq=baab1d32000cc23a&oq=marven&rsv_t=7070LR1tE3tUJ7AaQO8Va50%2BNEhh327GKDbWEa4KrPoCbz%2Fv9Xxpyitx6xU&euri=1771e9d29f0e48b1ae808b9ab296bce5",
        "nginx.jpg");

    downloadPicThread1.start();
    downloadPicThread2.start();
    downloadPicThread3.start();

  }
}

//下载器
class WebDownloader {

  //下载方法
  public void downloader(String url, String name) {
    try {
      FileUtils.copyURLToFile(new URL(url), new File(name));
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("IO异常-downloader");
    }
  }
}