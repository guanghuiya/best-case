package com.meiqiu.bestCaseV1.default关键字用处;

import org.springframework.stereotype.Service; /**
 * 实现类
 */
@Service
public class MyInterfaceImpl implements MyInterface {
    @Override
    public void test() {
        System.out.println("test 1");
    }
}
