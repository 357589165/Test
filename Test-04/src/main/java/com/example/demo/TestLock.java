package com.example.demo;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author 王超 by 2019-03-11
 */
public class TestLock implements Lock {

    private AtomicReference<Thread> af = new AtomicReference<>();

    private Queue<Thread> queue = new LinkedBlockingQueue<>();


    @Override
    public void lock() {
        while (!af.compareAndSet(null, Thread.currentThread())){
            queue.add(Thread.currentThread());
            LockSupport.park();
            queue.remove(Thread.currentThread());
        }
    }

    @Override
    public void unlock() {
        if(af.compareAndSet(Thread.currentThread(), null)){
            for(Thread t : this.queue){
                LockSupport.unpark(t);
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }
}
