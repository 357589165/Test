package com.example.demo;

import com.example.demo.test.AtomicTest;
import com.example.demo.test.CASTest;
import com.example.demo.test.IncrTest;

/**
 * @author 王超 by 2019-03-11
 */
public class TestApplication {

    private static IncrTest incrTest = new IncrTest();
    private static AtomicTest atomicInteger = new AtomicTest();
    private static CASTest casTest = new CASTest();

    /**
     * 测试某种锁时注释掉其他test 方法即可
     * @param args
     */
    public static void main(String[] args) {
        incrTest.test();
        atomicInteger.test();
        casTest.test();
    }

}
