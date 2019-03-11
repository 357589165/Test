package com.example.demo.test;

import com.example.demo.lock.TestLock;

/**
 * @author 王超 by 2019-03-11
 * 使用自定义编写的Lock 锁，实现CAS 锁机制。
 */
public class CASTest implements Test {

    private TestLock testLock = new TestLock();
    private int i=0,j=0;


    @Override
    public void test() {
        System.out.println("开始自写CAS 机制锁测试");
        this.loop(() -> {
            testLock.lock();
            i++;j++;
            testLock.unlock();
            System.out.println(i);
            System.out.println(j);
        });
    }
}
