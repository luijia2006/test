package com.we.basics.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        testRandomAccess();
    }

    /**
     * 1.RandomAccess接口这个空架子的存在，是为了能够更好地判断集合是否ArrayList或者LinkedList，
     * 从而能够更好选择更优的遍历方式，提高性能！
     * 2.通过查看源binarySearch代码，发现实现RandomAccess接口的List集合采用一般的for循环遍历，
     * 而未实现这接口则采用迭代器
     */
    public static void testRandomAccess() {
        //ArrayList实现RandomAccess接口
        List<String> list = new ArrayList<>();
        list.add("world1");
        list.add("hello");
        list.add("world");
        int index = Collections.binarySearch(list, "hello");
        System.out.println("============"+index);

        //ArrayList未实现RandomAccess接口
        List<String> list1 = new LinkedList<>();
        list1.add("world1");
        list1.add("hello");
        list1.add("world");
        int index1 = Collections.binarySearch(list1, "hello");
        System.out.println("============"+index1);
    }
}
