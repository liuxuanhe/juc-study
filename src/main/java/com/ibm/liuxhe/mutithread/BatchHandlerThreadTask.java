package com.ibm.liuxhe.mutithread;

import java.util.List;
import java.util.Map;

/**
 * 批量合规的多线程调用任务
 *
 * @version V1.0
 * @Package com.tydic.jtcrm.batch.task
 * @ClassName BatchHandlerThreadTask.java
 * @date 2019年4月4日 上午10:36:03
 */
public class BatchHandlerThreadTask implements Runnable {

  //待处理数据集合
  private List dataList;
  //其他参数Map
  private Map paramMap;

  public BatchHandlerThreadTask() {
    super();
  }

  public BatchHandlerThreadTask(List dataList, Map paramMap) {
    super();
    this.dataList = dataList;
    this.paramMap = paramMap;
  }

  public List getDataList() {
    return dataList;
  }

  public void setDataList(List dataList) {
    this.dataList = dataList;
  }

  public Map getParamMap() {
    return paramMap;
  }

  public void setParamMap(Map paramMap) {
    this.paramMap = paramMap;
  }


  @Override
  public void run() {
    long t1 = System.currentTimeMillis();

    for (int y = 0; y < dataList.size(); y++) {

      String s = (String) dataList.get(y);
//            System.out.println("--t--线程名: " + Thread.currentThread().getName() + "--当前批次处理总数据" + dataList.size() + "--当前数据---" + s);
      try {
        Thread.sleep(60);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println(
        "--h--线程名: " + Thread.currentThread().getName() + "--当前线程耗时：" + (System.currentTimeMillis()
            - t1) + "--当前批次处理总数据" + dataList.size());
  }

}