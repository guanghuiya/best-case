package com.meiqiu.bestCaseV1.相同订单串行_不同并行;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 假设有一个创建订单的接口，参数为订单 id，需实现，高并发场景下，订单 id 相同请求串行执行，不同请求并行执行。
 * @Author sgh
 * @Date 2025/3/4
 * @Time 12:21
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * 借助Java 的 ConcurrentHashMap 来为每个订单 ID 维护一个独立的锁，再结合 ReentrantLock（可重入锁） 来确保同一订单 ID 的操作是串行的
     */
    private static final ConcurrentHashMap<String, ReentrantLock> locks = new ConcurrentHashMap<>();

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public static void createOrder(@RequestBody CreateOrderDTO orderDTO) {
        String orderId = orderDTO.getOrderId();
        //获取ReentrantLock锁，computeIfAbsent存在就返回，不存在就添加值返回
        ReentrantLock lock = locks.computeIfAbsent(orderId, key -> new ReentrantLock());
        lock.lock();
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + " 正在处理订单：" + orderId);
            //模拟处理耗时
            Thread.sleep(1000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + " 处理订单：" + orderId + " 完成");
        } catch (Exception e) {
            //线程中断
            Thread.currentThread().interrupt();
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // 创建多个线程来模拟并发请求
        Thread t1 = new Thread(() -> createOrder(new CreateOrderDTO("1")));
        Thread t2 = new Thread(() -> createOrder(new CreateOrderDTO("2")));
        Thread t3 = new Thread(() -> createOrder(new CreateOrderDTO("3")));
        Thread t4 = new Thread(() -> createOrder(new CreateOrderDTO("1")));

        // 启动线程
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            // 等待所有线程执行完毕
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

