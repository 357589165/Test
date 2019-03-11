package com.example.demo.test;

/**
 * @author 王超 by 2019-03-11
 * 测试默认JDK 高并发变量自增
 */
public class IncrTest implements Test{

    private int i = 0, j = 0;

    @Override
    public void test() {
        System.out.println("测试变量自增操作");
        this.loop(() -> {
            i++; j++;
            System.out.println(i);
            System.out.println(j);
        });
    }
}
