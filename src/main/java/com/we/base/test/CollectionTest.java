package com.we.base.test;

import com.we.core.common.util.CollectionUtil;
import com.we.core.common.util.Util;

import java.util.List;

public class CollectionTest {
    public static void main(String[] args) {
        //是否是空方法测试
        List list = CollectionUtil.list();
        if (Util.isEmpty(list)) {
            System.out.println("true");
        }
    }
}
