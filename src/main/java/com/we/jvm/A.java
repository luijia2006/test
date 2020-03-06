package com.we.jvm;

import org.openjdk.jol.info.ClassLayout;

public class A {
    boolean flag = false;

    public static void main(String[] args){
        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}