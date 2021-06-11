package com.ibm.liuxhe.lambda;

// 推导lamdba表达式
public class TestLamdba1 {


  // 3.静态内部类
  static class Like2 implements ILike {

    @Override
    public void lambda() {
      System.out.println("I like lambda2");
    }
  }

  public static void main(String[] args) {
    ILike like = new Like1();
    like.lambda();

    like = new Like2();
    like.lambda();

    // 4.局部内部类
    class Like3 implements ILike {

      @Override
      public void lambda() {
        System.out.println("I like lambda3");
      }
    }

    like = new Like3();
    like.lambda();

    // 5. 匿名内部类，没有类的名称，必须借助接口或者父类
    like = new ILike() {
      @Override
      public void lambda() {
        System.out.println("I like lambda4");
      }
    };

    like.lambda();

    // 6. 用lambda
    like = () -> {
      System.out.println("I like lambda5");
    };

    like.lambda();
  }

}

// 1. 定义一个接口
interface ILike {

  void lambda();
}

// 2. 定义一个实现类
class Like1 implements ILike {

  @Override
  public void lambda() {
    System.out.println("I like lambda");
  }
}