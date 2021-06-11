package com.ibm.liuxhe.issue.readfile2;


public class WordFreStatisTest {

  public static void main(String[] args) throws Exception {

    WordFreStatis word = new WordFreStatis(
        "/Users/liuxhe/Documents/GitHub/my-app/src/main/java/com/ibm/liuxhe/issue/readfile");
//		word.threadPool();
//		word.singleThread();
//		word.mutilThread();
    word.threadPoolInFile();
  }

}
