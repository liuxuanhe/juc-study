package com.ibm.liuxhe.fanshe;

import com.ibm.liuxhe.entity.User;
import java.lang.reflect.Field;

public class app_02 {

  public static void main(String[] args) throws Exception {
    //1.获取Class对象
    Class stuClass = Class.forName("com.ibm.liuxhe.entity.User");
    //2.获取字段
    System.out.println("************获取所有公有的字段********************");
    Field[] fields = stuClass.getFields();
    for (Field field : fields) {
      System.out.println(field);
    }

    System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
    fields = stuClass.getDeclaredFields();
    for (Field field : fields) {
      System.out.println(field);
    }

    System.out.println("*************获取公有字段**并调用***********************************");
    Field field = stuClass.getField("publicName");
    System.out.println(field);

    Object obj = stuClass.getConstructor()
        .newInstance();//产生Student对象--》Student stu = new Student();
    field.set(obj, "刘德华");

    //验证
    User user = (User) obj;
    System.out.println("验证姓名：" + user.getPublicName());

    System.out.println("**************获取私有字段****并调用********************************");
    field = stuClass.getDeclaredField("userName");
    System.out.println(field);
    field.setAccessible(true);

    field.set(obj, "aaaaa");
    System.out.println(user.toString());
  }
}
