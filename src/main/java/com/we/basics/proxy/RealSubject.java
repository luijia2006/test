package com.we.basics.proxy;

//真实角色：实现了Subject的request()方法
public class RealSubject implements Subject{  
      
    public void request(){  
        System.out.println("From real subject.");  
    }  
} 