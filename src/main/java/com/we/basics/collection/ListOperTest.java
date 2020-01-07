package com.we.basics.collection;

import java.util.LinkedList;
import java.util.List;

public class ListOperTest {
    public static void main(String[] args) {
//        System.out.println("result = " + testAdd() );
        System.out.println("result = " + testReplace() );
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


    public static String testReplace() {
        List<String> elements = new LinkedList();
        elements.add("a");
        elements.add("b");
        elements.add("c");
        String content = "abcd";

        String pureContent = content;
        for (String element:elements) {
            pureContent=pureContent.replace(element,"");
        }
        return pureContent;
    }
}
