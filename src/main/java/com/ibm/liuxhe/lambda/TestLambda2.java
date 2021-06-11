package com.ibm.liuxhe.lambda;

public class TestLambda2 {

  public static void main(String[] args) {
    ILove love = new Love();
    love.love(1, 2);

    // 1. lambda
    love = (int a, int b) -> {
      System.out.println("I love --- " + a);
    };

    // 2. lambda 简化参数类型
    love = (a, b) -> {
      System.out.println("I love --- " + a);
      System.out.println("I love --- " + b);
    };

//    // 3. lambda 简化括号
//    love = a -> {
//      System.out.println("I love --- " + a);
//    };
//
//    // 4. lambda 简化花括号 (代码只能有1行）
//    love = a -> System.out.println("I love --- " + a);

    love.love(520, 1234);

    // 总结：
    // lambda表达式只能有一行代码的情况下，才能简化成一行
    // 如果有多行，需要用代码块包裹
    // 必须是函数式接口 （一个接口只能有一个方法）
    // 多个参数也可以去掉参数类型，要去掉就都去掉
  }
}

interface ILove {

  void love(int a, int b);
}

class Love implements ILove {

  @Override
  public void love(int a, int b) {
    System.out.println("I Love " + a);
    System.out.println("I Love " + b);
  }
}