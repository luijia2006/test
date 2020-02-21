package com.we.basics.classloader.custom;

public class ClassLoaderChecker {
    
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader("D:\\", "random");
        Class c = myClassLoader.loadClass("Hello");
        System.out.println("ClassLoader:" + c.getClassLoader());
        Object instance = c.newInstance();
    }

}