package com.we.basics.collection;

import java.util.LinkedList;
import java.util.List;

public class ListOperTest {
    public static void main(String[] args) {
        System.out.println("args = [" + testAdd() + "]");
    }

    public static String testAdd() {
        List<String> elements = new LinkedList();
        elements.add("a");
        elements.add("b");
        elements.add("c");
        List currElements = new LinkedList();
        currElements.add("d");
        currElements.add("e");
        elements.addAll(1, currElements);

        return elements.toString();
    }
}
