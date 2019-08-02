package com.we.basics.algorithm;

import com.we.core.common.util.MapUtil;
import com.we.core.common.util.Util;

import java.util.Map;

/**
 * 提取出最大连续数字
 */
public class MaxContinueNumber {

    public static Map<String,Integer> getMaxContinueNumber(String text) {
        Map<String,Integer> map = MapUtil.map();
        String sub = "";
        for (int i=0;i<text.length();i++) {
            char c = text.charAt(i);
            //如果是数字
            if (c >= '0' && c <= '9') {
                //如果为空
                if (Util.isEmpty(sub)) {
                    sub = String.valueOf(c);
                } else {
                    //如果前一个大于下一个
                    if (sub.charAt(sub.length() - 1) >= c) {
                        map.put(sub, map.get(sub) == null ? 1 : map.get(sub)+1);
                        sub = String.valueOf(c);
                    } else {
                        sub += String.valueOf(c);
                    }
                }
            } else {
                //如果不是数字
                if (!Util.isEmpty(sub)) {
                    map.put(sub, map.get(sub) == null ? 1 : map.get(sub)+1);
                    sub = "";
                }
            }
            //最后一个
            if (i==text.length()-1) {
                if (!Util.isEmpty(sub)) {
                    map.put(sub, map.get(sub) == null ? 1 : map.get(sub)+1);
                    sub = "";
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        String text = "asd12354cvbcv123123cvbcvb123456";
        System.out.println(getMaxContinueNumber(text));
    }
}
