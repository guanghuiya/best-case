package com.meiqiu.bestCase.default关键字用处;

/**
 * 接口
 */
public interface MyInterface {

    /**
     * 抽象方法
     */
    public void test();

    /**
     * 默认方法（jdk1.8以后）
     */
    public default void test2() {
        System.out.println("test 2");
    }
}
