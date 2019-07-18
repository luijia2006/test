package com.we.basics.thread.threadlocal;

import com.we.core.common.util.Util;

import java.util.HashMap;

public class TestThreadLocal {
    public static ThreadLocal<HashMap<Long,Integer>> roleLocal = new ThreadLocal();

    private void setValue() {
        long sendId=123;
        HashMap<Long, Integer> stringObjectHashMap = roleLocal.get();
        if (Util.isEmpty(stringObjectHashMap)) {
            stringObjectHashMap = new HashMap<>();
        }
        int userRole = stringObjectHashMap.get(sendId)==null?0:stringObjectHashMap.get(sendId);
        if (userRole==0) {
            userRole = 1;
            stringObjectHashMap.put(sendId, userRole);
            roleLocal.set(stringObjectHashMap);
        }
    }

    public static void main(String[] args) {
        TestThreadLocal local = new TestThreadLocal();
        local.setValue();
    }
}
