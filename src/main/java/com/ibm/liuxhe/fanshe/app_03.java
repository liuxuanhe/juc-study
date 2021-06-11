package com.ibm.liuxhe.fanshe;

import java.lang.reflect.Method;

public class app_03 {

  public static void main(String[] args) throws Exception {
    //1.获取Class对象
    Class stuClass = Class.forName("com.ibm.liuxhe.entity.Student");
    //2.获取所有公有方法
    System.out.println("***************获取所有的”公有“方法*******************");
    Method[] methods = stuClass.getMethods();
    for (Method method : methods) {
      System.out.println(method);
    }

    System.out.println("***************获取所有的方法，包括私有的*******************");
    methods = stuClass.getDeclaredMethods();
    for (Method m : methods) {
      System.out.println(m);
    }

    System.out.println("***************获取公有的show1()方法*******************");
    Method method = stuClass.getMethod("show1", String.class);
    System.out.println(method);

    //实例化一个Student对象
    Object obj = stuClass.getConstructor().newInstance();
    method.invoke(obj, "刘德华");

    System.out.println("***************获取私有的show4()方法******************");
    method = stuClass.getDeclaredMethod("show4", int.class);
    method.setAccessible(true);
    Object result = method.invoke(obj, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
    System.out.println("返回值：" + result);
  }
}
