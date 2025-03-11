package com.meiqiu.bestCaseV1.threadLocal用法;

/**
 * @Description ThreadLocal 是 Java 中的一个类，它为每个使用该变量的线程都提供一个独立的变量副本，每个线程都可以独立地改变自己的副本，而不会影响其他线程所对应的副本
 * 每一个线程都有 ThreadLocalMap变量，里面是一个个 entry 对象，存储当前线程的threadLocal 数据,每个 entry 对象都包含了一个 key 和 value，key 是 ThreadLocal 对象，value 是线程私有的变量副本。
 * @Author 胖辉
 * @Date 2025/3/6
 * @Time 10:09
 */
public class Test {

    //常见的场景：1、保存线程上下文信息 2、避免参数传递
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static void main(String[] args) {
        //创建两个线程，分别进行赋值、取值、删除操作
       Thread t1 = new Thread(() -> {
           //赋值
           threadLocal.set("t1");
           //取值
           System.out.println("线程t1:" + threadLocal.get());
           try {
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           //删除
           threadLocal.remove();
           System.out.println("线程t1删除后:" + threadLocal.get());
       });

        Thread t2 = new Thread(() -> {
            //赋值
            threadLocal.set("t2");
            //取值
            System.out.println("线程t2:" + threadLocal.get());
            //删除
            threadLocal.remove();
            System.out.println("线程t2删除后:" + threadLocal.get());
        });

        t1.start();
        t2.start();
    }





}
