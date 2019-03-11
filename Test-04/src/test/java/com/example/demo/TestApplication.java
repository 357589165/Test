package com.example.demo;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王超 by 2019-03-10
 */
public class TestApplication {

    private CountDownLatch countDownLatch = new CountDownLatch(20000);
    private TestLock testLock = new TestLock();
    private int i = 0, j = 0;
    private AtomicInteger ai = new AtomicInteger(0);
    private AtomicInteger aj = new AtomicInteger(0);

    /**
     * 多线程情况下的i++ 会造成线程安全问题
     * 初次测试结果的两个变量，最终值并不会是20000.
     */
    @Test
    public void testNoLock(){
        for (int z = 0; z< 20000 ; z++){
            new Thread(() -> {
                this.i++;
                this.j++;
                this.waitLatch();
            }).start();
        }
        System.out.println(i);
        System.out.println(j);
    }

    @Test
    public void testAtomicLock(){
        for (int z = 0; z < 20000 ; z++){
            new Thread(() -> {
                this.ai.incrementAndGet();
                this.aj.incrementAndGet();
                this.waitLatch();
            }).start();
        }
        System.out.println(ai.get());
        System.out.println(aj.get());
    }

    @Test
    public void testReentrantLock(){
        ReentrantLock reentrantLock = new ReentrantLock();
        for (int z = 0; z < 20000 ; z++){
            new Thread(() -> {
                reentrantLock.lock();
                try{
                    this.ai.getAndIncrement();
                    this.aj.getAndIncrement();
                }finally {
                    reentrantLock.unlock();
                }
            }).start();
        }
        System.out.println(ai);
        System.out.println(aj);
    }

    @Test
    public void testCASLock(){
        for (int z = 0; z < 20000 ; z++){
            new Thread(() -> {
                testLock.lock();
                this.i++;
                this.j++;
                testLock.unlock();
            }).start();
        }
        System.out.println(i);
        System.out.println(j);
    }


    private void waitLatch(){
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
