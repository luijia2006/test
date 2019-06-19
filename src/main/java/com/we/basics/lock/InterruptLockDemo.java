package com.we.basics.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by qhy on 2018/10/27/027
 * 演示中断锁
 */
public class InterruptLockDemo {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock(true);// 得到可中断锁

        Info2 info = new Info2(reentrantLock);

        Thread writeThread = new Thread(new Write2(info));
        Thread readThread = new Thread(new Read2(info));

        writeThread.start();
        Thread.sleep(100); // 主线程睡眠100毫秒，让write线程先执行
        readThread.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                for (;;){
                    if(System.currentTimeMillis() - start > 5000){
                        System.out.println("不等了，我都等了5秒了");
                        readThread.interrupt();
                        break;
                    }
                }
            }
        }).start();

    }

}

class Info2 {
    private ReentrantLock lock;

    public Info2(ReentrantLock lock) {
        this.lock = lock;
    }

    public void write(){
        lock.lock();
        try {
            long strat = System.currentTimeMillis();
            System.out.println("begin write.");
            for (;;){
                //if(System.currentTimeMillis() - strat > Integer.MAX_VALUE)
                if(System.currentTimeMillis() - strat > 10*1000)
                    break;;
            }// 模拟长时间写
            System.out.println("write over....");
        }  finally {
            System.out.println("finally write over....");
            lock.unlock(); // ReentrantLock的加锁和释放锁
        }
    }

    public void read() throws InterruptedException {
        lock.lockInterruptibly(); // 将查中断锁
        try {
            System.out.println("begin read");
        }finally {
            System.out.println("end read");
            lock.unlock();
        }
    }
}

class Write2 implements Runnable{

    private Info2 info;

    public Write2(Info2 info) {
        this.info = info;
    }

    @Override
    public void run() {
        info.write();
    }
}

class Read2 implements Runnable{
    private Info2 info;

    public Read2(Info2 info) {
        this.info = info;
    }

    @Override
    public void run() {
        try {
            info.read();
        } catch (InterruptedException e) {
            System.out.println("读 被中断了");
        }
        System.out.println("读 结束了");
    }
}