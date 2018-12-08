package com.we;

import com.we.core.common.util.CollectionUtil;
import com.we.core.common.util.Util;

import java.util.List;

public class TestEmpty {

    public static void main(String[] args) {
        List list = CollectionUtil.list();
        if (Util.isEmpty(list)) {
            System.out.println("true");
        }
    }

}


