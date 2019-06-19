package com.we.basics.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by qhy on 2018/11/8/008
 *  Semaphore 一个计数器信号量
 *      我的理解是类似于 固定大小的线程池 FixThreadPool
 */
public class SemaphoreDemo {

    public void a(Semaphore semaphore){
        try {
            int n = semaphore.availablePermits();
            if(n <= 0){
                System.out.println("当前信号量没有足够的许可需要等待。。");
            }
            semaphore.acquire(2);
            System.out.println(Thread.currentThread().getName()+"  print a, 可用许可数量为："+n);
            Thread.sleep(1000); // 睡眠1秒，等待其他线程释放许可
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();

        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);// 设置10的固定访问名额
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();

        /*
            需要执行的线程数小于10，不需要等待执行
            需要执行的线程数大于10，大于10 的部分需要等到其他线程释放许可后，才可以继续获取
                源码阅读。。
                底层再次使用到了AQS
         */
        for (int i = 0; i < 12; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    semaphoreDemo.a(semaphore);
                }
            }).start();
        }
    }

}
