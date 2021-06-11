package com.ibm.liuxhe.fanshe;

import com.ibm.liuxhe.entity.User;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class app_01 {

  public static void main(String[] args) throws Exception {
    m2();
  }

  public static void m1() {
    //第一种方式获取Class对象
    User user = new User();
    Class userClass = user.getClass();
    System.out.println(userClass.getName());

    //第二种方式获取Class对象
    Class userClass2 = User.class;
    System.out.println(userClass2.getName());

    //第三种方式获取Class对象
    try {
      Class userClass3 = Class.forName("com.ibm.liuxhe.entity.User");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
      System.out.println(userClass3.getName());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void m2()
      throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Class clazz = Class.forName("com.ibm.liuxhe.entity.Student");
    //2.获取所有公有构造方法
    System.out.println("**********************所有公有构造方法*********************************");
    Constructor[] constructors = clazz.getConstructors();

    for (Constructor constructor : constructors) {
      System.out.println(constructor);
    }

    System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
    constructors = clazz.getDeclaredConstructors();

    for (Constructor constructor : constructors) {
      System.out.println(constructor);
    }

    System.out.println("*****************获取公有、无参的构造方法*******************************");
    Constructor con = clazz.getConstructor(null);
    Object obj = con.newInstance();
    System.out.println(con);

    System.out.println("******************获取私有构造方法，并调用*******************************");
    con = clazz.getDeclaredConstructor(char.class);
    System.out.println(con);

    con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
    obj = con.newInstance('男');
  }
}
