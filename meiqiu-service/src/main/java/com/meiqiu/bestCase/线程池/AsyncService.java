package com.meiqiu.bestCase.线程池;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncService {

    @Async("restExecutor")
    public void executeAsyncTask1() {
        // 异步执行的任务
        for (int i = 0; i < 100; i++) {
            log.info("executeAsyncTask-1:{}", i);
        }
    }

    @Async("restExecutor")
    public void executeAsyncTask2() {
        // 异步执行的任务
        for (int i = 0; i < 100; i++) {
            log.info("executeAsyncTask-2:{}", i);
        }
    }
}
