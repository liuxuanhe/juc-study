package com.ibm.liuxhe.offer.赋值运算符函数;

public class FuZhiYunSuan {

  public static void main(String[] args) {
    testMyString();
  }

  public static void testMyString() {
    MyString s1 = new MyString("a");
    MyString s2 = new MyString("b");
    MyString s3 = new MyString("c");

    System.out.println((s1.assign(s2).assign(s3)));

    System.out.println("s1: " + s1);
    System.out.println("s2: " + s2);
    System.out.println("s3: " + s3);
  }

  public static class MyString {

    private String data;

    public MyString(String data) {
      this.data = data;
    }

    public MyString assign(final MyString another) {
      if (this == another || this.data.equals(another)) {
        return this;
      } else {
        this.data = another.data;
        return this;
      }
    }

    @Override
    public String toString() {
      return "MyString{" +
          "data='" + data + '\'' +
          '}';
    }
  }

}
