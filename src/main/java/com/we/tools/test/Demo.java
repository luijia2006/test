package com.we.tools.test;

class SubClass extends SuperClass
{
    public String name = "SubClass";
}

class SuperClass
{
    public String name = "SuperClass";
}

public class Demo
 {
    public static void main(String[] args) 
    {
        SuperClass clz = new SubClass();
        //你觉得这里输出什么?
        System.out.println(clz.name);
    }
}