package com.ibm.liuxhe.mutithread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileThread {

  public static void main(String[] args) throws Exception {

    // 启动时间
    long t1 = System.currentTimeMillis();

    // 文件地址
    String localFilePath = "/Users/liuxhe/Documents/Key/1.txt";

    // 开启固定线程池
    ExecutorService exec = Executors.newFixedThreadPool(50);

    // 逐行读取本地文件
    List<String> dataList = new ArrayList<>();

    // 初始 File 类
    File file = new File(localFilePath);

    // 生成文件流
    InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "GBK");

    // 从字符输入流中读取文本并缓冲字符
    BufferedReader br = new BufferedReader(reader);

    String str = null;

    // 定义计数器
    int i = 0;

    // 循环读行
    while ((str = br.readLine()) != null) {
      // i 增加
      i++;

      // 加入数组
      dataList.add(str);

      if (i % 100 == 0) {
        System.out.println("i" + i);
        // 每次传入线程的集合
        List<String> onceList = new ArrayList<>();
        for (String item : dataList) {
          onceList.add(item);
        }
        // 清空集合
        dataList = null;
        // 重构集合
        dataList = new ArrayList<String>();

        Map<String, Object> pMap = new HashMap<>();
        // 开启线程
        Runnable task = new BatchHandlerThreadTask(onceList, pMap);
        exec.submit(task);
      }
    }
    reader.close();
    br.close();

    // 判断最后一次
    if (dataList.size() != 0) {
      Map<String, Object> pMap = new HashMap<>();
      Runnable task = new BatchHandlerThreadTask(dataList, pMap);
      exec.submit(task);
    }

    exec.shutdown();
    while (true) {
      if (exec.isTerminated()) {
        System.out.println("全部线程都结束了，i: " + i + "耗时：" + (System.currentTimeMillis() - t1));
        break;
      }
    }

  }


}