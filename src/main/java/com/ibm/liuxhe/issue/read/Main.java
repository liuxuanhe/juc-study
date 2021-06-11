package com.ibm.liuxhe.issue.read;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  private static final ConcurrentMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();

  public static void main(String[] args) throws IOException {
    Path p = Paths.get(args[0]);
    List<String> lines = Files.readAllLines(p, StandardCharsets.UTF_8);
    ExecutorService service = Executors.newFixedThreadPool(10);
    for (String line : lines) {
      //new Thread(new MyCounter(line,map)).start();
      service.execute(new MyCounter(line, map));
    }
    service.shutdown();
    while (!service.isTerminated()) {
      ;
    }
    Iterator i = map.keySet().iterator();

    while (i.hasNext()) {
      String word = (String) i.next();
      int count = map.get(word);
      System.out.println(word + " " + count);
    }
  }
}
