package com.we.basics.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName MyLock
 * @Auther 160522
 * @Date 2018/10/30 9:09
 * 使用AQS来实现自定义可重入锁
 **/
public class MyLock implements Lock {
    // 使用AQS的子类来进行锁的管理
    private Helper helper = new Helper();

    @Override
    public void lock() {
        helper.acquire(1); // 使用AQS的方法
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        helper.release(1); // 释放锁
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    // 对于继承AQS的类，官网建议使用非公共内部类的形式
    // 这里仅仅实现独占式的， 这里还要实现可重入的
    // 这里可以参考 ReentrantLock的实现方式
    class Helper extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            /**
             * 锁状态为0时，表示没有被占用，这是就可以占用，将锁的标志位+1
             * 如果锁的状态不为0，表示已经被其他线程占用，那么就需要判断被占用的线程是否为当前线程
             * 如果是当前线程则可以重入，需要将锁标志位+1
             * 如果不是当前线程表示为其他线程，则返回false
             */
            Thread cur = Thread.currentThread();
            int state = getState(); // 调用AQS的方法，获取线程状态
            if(state == 0){ // 线程第一次进来，直接设置状态和线程持有者
                if(compareAndSetState(0,arg)){
                    setExclusiveOwnerThread(cur);
                    return true;
                }
            }else if(cur == getExclusiveOwnerThread()){
                // 只有当前线程进来不存在线程安全问题，所以可以不使用cas
                // 其他线程进来判断是否为当前线程
                if(state + arg < 0)
                    throw new Error("Maximum lock count exceeded");
                setState(state +arg); // 锁标志位+1，如果是不可重入锁，就只有1和0
                return true;
            }
            // 其他情况返回false
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            /**
             * 线程释放锁，需要将锁标志位-1，判断当前线程是否为线程占有者，
             */
            boolean free = false;
            // 将锁标志位-1，为0时要释放线程占有者
            int state = getState();
            if(Thread.currentThread() != getExclusiveOwnerThread()){
                throw new IllegalMonitorStateException();
            }
            if(state - arg == 0){ // 当前线程才能删除，不存在线程安全问题
                setExclusiveOwnerThread(null);
                free = true;
            }
            setState(state - arg); // 设置标志位
            return free;
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }
    }

}
