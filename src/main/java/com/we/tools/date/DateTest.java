package com.we.tools.date;

import com.we.core.common.util.DateUtil;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DateTest {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(100);

        //线程不安全，会出问题
        for (int i = 0; i < 200; i++) {
            service.execute(() -> {
                for (int j = 0; j < 100; j++) {
                    try {
                        System.out.println(DateUtil.Date2String(new Date()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        // 等待上述的线程执行完
        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
    }
}
