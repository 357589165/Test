package com.example.demo.test;

import java.util.concurrent.CountDownLatch;

/**
 * 供主线程调用使用接口。
 * 各锁测试类实现该接口，主线程调用其{@link #test()} 方法，进行测试其相应功能
 */
public interface Test {

    /* 内置CountDownLatch 类进行线程控制 */
    CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(20000);

    /* 需实现该类进行逻辑编写 */
    void test();

    /* 传入需要测试的Runnable 类 */
    default void loop(Runnable runnable){
        this.loop(COUNT_DOWN_LATCH, runnable);
    }

    /* 传入需要测试的Runnable 类，可自定义CountDownLatch 工具 */
    default void loop(CountDownLatch latch, Runnable runnable){
        int count = (int) latch.getCount();

        for(int i=0; i < count ; i++){

            new Thread(runnable).start();

            latch.countDown();
        }
    }
}
