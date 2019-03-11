package com.example.demo.test;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 王超 by 2019-03-11
 * 使用Atomic 类进行测试并发自增
 */
public class AtomicTest implements Test {

    private AtomicInteger i = new AtomicInteger(0), j = new AtomicInteger(0);

    @Override
    public void test() {
        System.out.println("测试Atomic 原子操作");
        this.loop(() -> {
            i.getAndIncrement();
            j.getAndIncrement();

            System.out.println(i.get());
            System.out.println(j.get());
        });
    }

}
