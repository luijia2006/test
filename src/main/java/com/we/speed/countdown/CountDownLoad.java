package com.we.speed.countdown;

import com.we.speed.BaseResult;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
 
/**
 * @Description:
 * @Author: yangzl2008
 * @Date: 2016/1/9 19:06
 */
public class CountDownLoad {

    public static void main(String args[]) throws Exception{
        CountDownLoad parallel = new CountDownLoad();
        parallel.test03();

    }
 
    public void test03() throws InterruptedException {
 
        long start = System.currentTimeMillis();

        CountDownLatch latch = new CountDownLatch(2);
        BaseResult baseResult1 = method1(latch);// 耗时操作1，时间 time1
        BaseResult baseResult2 = method2(latch);// 耗时操作2，时间 time2
        latch.await();
        long end = System.currentTimeMillis();
 
        //总耗时 time = time1 + time2
        System.out.println("baseResult1 is " + baseResult1 + "\nbaseResult2 is " + baseResult2 + "\ntime cost is " + (end - start));
    }
 
    private BaseResult method1(CountDownLatch latch) {
        BaseResult baseResult = new BaseResult();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    baseResult.setCode(1);
                    baseResult.setMsg("method1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    latch.countDown();
                }
            }
        }).start();
        latch.countDown();
        return baseResult;
    }
 
    private BaseResult method2(CountDownLatch latch) {
        BaseResult baseResult = new BaseResult();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    baseResult.setCode(1);
                    baseResult.setMsg("method2");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return baseResult;
    }
 
}