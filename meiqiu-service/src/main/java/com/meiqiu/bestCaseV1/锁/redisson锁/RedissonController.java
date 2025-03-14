package com.meiqiu.bestCaseV1.锁.redisson锁;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author 胖辉
 * @Date 2025/3/14
 * @Time 13:10
 */
@RestController
@RequestMapping("/redisson")
public class RedissonController {

    private final RedissonClient redissonClient;

    public RedissonController(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @RequestMapping("/lock")
    public String lock() {
        String key = "redissonLock";
        try {
            RLock lock = redissonClient.getLock(key);
            boolean isLock = false;
            try {
                // 尝试获取锁，等待 10 秒，锁自动释放时间为 30 秒
                isLock = lock.tryLock(10, 30, TimeUnit.SECONDS);
                if (isLock) {
                    System.out.println(Thread.currentThread().getName() + " 获取锁成功,模拟业务逻辑");
                    Thread.sleep(1000);
                } else {
                    System.out.println(Thread.currentThread().getName() + " 获取锁失败");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                if (isLock && lock.isHeldByCurrentThread()) {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " 释放锁");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 任务执行完毕");
        return "success";
    }

    @RequestMapping("/test")
    public void test() {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                lock();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                lock();
            }
        }).start();
    }
}
