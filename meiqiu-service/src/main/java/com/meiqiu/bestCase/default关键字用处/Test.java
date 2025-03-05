package com.meiqiu.bestCase.default关键字用处;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description default关键字的使用场景
 * @Author 胖辉
 * @Date 2025/3/4
 * @Time 14:57
 */
public class Test {

    /**
     * 1、switch语句的默认情况
     *
     * @param args
     */
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            switch (i) {
                case 1:
                    System.out.println("case 1");
                    break;
                case 2:
                    System.out.println("case 2");
                    break;
                default:
                    System.out.println("default " + i);
                    break;
            }

        }
    }

    /**
     * 2、接口的默认方法
     */
    @Autowired
    private MyInterface myInterface;

    public void test() {
        myInterface.test();

        //接口的默认方法
        myInterface.test2();
    }

}

