package com.ibm.liuxhe.issue.read;

import java.util.concurrent.ConcurrentMap;


public class MyCounter implements Runnable {

  private ConcurrentMap<String, Integer> map = null;
  private String line = null;

  public MyCounter(String line, ConcurrentMap<String, Integer> map) {
    this.line = line;
    this.map = map;
  }

  @Override
  public void run() {
    for (String word : line.split(" ")) {
      //System.out.println(word);
      increase(word);
    }
  }

  public int increase(String word) {
    Integer oldValue;
    Integer newValue;
    while (true) {
      oldValue = map.get(word);
      if (oldValue == null) {
        newValue = 1;
        if (map.putIfAbsent(word, newValue) == null) {
          break;
        }
      } else {
        newValue = oldValue + 1;
        if (map.replace(word, oldValue, newValue)) {
          break;
        }
      }
    }
    return newValue;
  }
}
