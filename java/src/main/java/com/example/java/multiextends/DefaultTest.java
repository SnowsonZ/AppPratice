package com.example.java.multiextends;

/**
 * @Author: Snowson
 * @Date: 2018/8/30 11:16
 * @Description:
 *
 * 接口新增修饰符
 * 1. default
 *     避免接口变动造成的所有实现类的改动，实现类默认实现该方法
       引入接口的多继承，造成冲突
            1.同时继承或实现拥有相同签名方法的接口后实现的接口方法被覆盖
            2.方法名相同但方法类型为基础类型系统根据默认选择调用的方法，比如参数类型分别为int和short，入参为byte，
              则会调用参数为short的接口方法
            3.继承类和接口中存在相同签名函数类的优先级高于接口，接口方法被覆盖
   2. static
        类似类的静态方法，可以使用接口调用，需要在接口中实现
 */
public class DefaultTest extends DefaultSuper implements DefaultFeature2, DefaultFeature {

    public static void main(String[] args) {
        DefaultFeature.fun2();
        DefaultFeature2.fun2();
        byte a = 1;
        DefaultTest test = new DefaultTest();
        test.fun1(a);
    }

    @Override
    public void fun1(long a) {

    }

    @Override
    public void fun3() {

    }
}
