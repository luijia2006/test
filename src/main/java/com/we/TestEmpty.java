package com.we;

import com.we.core.common.util.CollectionUtil;
import com.we.core.common.util.Util;

import java.time.LocalDateTime;
import java.util.*;

public class TestEmpty {

    public static void main(String[] args) {
        TestTime();
        testCalculate();
    }
    /**
     * 排除有重复连续数字的元素
     */
    public static void testExcludeDuplication() {
        List<String> list = CollectionUtil.list();
        list.add("456abc");
        list.add("abc1234");
        list.add("2345abc1234");
        list.add("abcefg456");

        Map<String, List<String>> elemap = getElemap(list);
        Iterator<Map.Entry<String, List<String>>> entries = elemap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, List<String>> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            entries.remove();
        }
    }

    private static Map<String, List<String>> getElemap(List<String> list) {
        Map<String, List<String>> map = new HashMap<>();
        list.forEach(p->{
            //0-9对应Ascii 48-57
            List<String> childList = CollectionUtil.list();
            StringBuffer sb = new StringBuffer();
            for (int i=0;i<p.length();i++) {
                char c = p.charAt(i);
                if (c>=48&&c<=57) {
                    //System.out.println("===" + c);
                    sb.append(c);
                    if (i<p.length()-1) {
                        if (p.charAt(i) + 1 == p.charAt(i+1)) {
                            continue;
                        }
                    }
                    if (sb.length()>1) {
                        childList.add(sb.toString());
                        sb.setLength(0);
                    }
                }
            }
            if (!Util.isEmpty(childList)) {
                map.put(p, childList);
            }
        });
        return map;
    }


    /**
     * 取整
     */
    public static void testCalculate() {
        int gainScore=10;
        int count=8;
        int rightCount=3;
        System.out.println(((rightCount*100/count)*gainScore)/100);
    }


    /**
     * 测试空
     */
    public static void testEmpty(){
        List list = CollectionUtil.list();
        if (Util.isEmpty(list)) {
            System.out.println("true");
        }
    }
    
    public static  void TestTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("==="+now);
        LocalDateTime waterStart = LocalDateTime.of(now.getYear(), now.getMonth(),now.getDayOfMonth(),3,0);
        System.out.println("==="+waterStart);
        LocalDateTime waterEndTime =LocalDateTime.of(now.getYear(), now.getMonth(),now.getDayOfMonth(),4,0);
        System.out.println("==="+waterEndTime);
    }

}


