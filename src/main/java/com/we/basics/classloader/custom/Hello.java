package com.we.basics.classloader.custom;

//编译需要把此类放到D盘，掉包名
//javac Hello.java
public class Hello {
    static {
        System.out.println("hello");
    }
}