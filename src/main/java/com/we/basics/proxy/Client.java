package com.we.basics.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 客户端：生成代理实例，并调用了request()方法
 * https://blog.csdn.net/jiankunking/article/details/52143504
 */
public class Client {  
  
    public static void main(String[] args) throws Throwable{  
        // TODO Auto-generated method stub
        //生成$Proxy0的class文件
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Subject rs=new RealSubject();//这里指定被代理类  
        InvocationHandler ds=new DynamicSubject(rs);
        Class<?> cls=rs.getClass();  
          
        //以下是一次性生成代理  
          
        Subject subject=(Subject) Proxy.newProxyInstance(
                cls.getClassLoader(),cls.getInterfaces(), ds);
        //createProxyClassFile(Subject.class);
          
        //这里可以通过运行结果证明subject是Proxy的一个实例，这个实例实现了Subject接口  
        System.out.println(subject instanceof Proxy);  
          
        //这里可以看出subject的Class类是$Proxy0,这个$Proxy0类继承了Proxy，实现了Subject接口  
        System.out.println("subject的Class类是："+subject.getClass().toString());  
          
        System.out.print("subject中的属性有：");  
          
        Field[] field=subject.getClass().getDeclaredFields();  
        for(Field f:field){
            System.out.print(f.getName()+", ");  
        }  
          
        System.out.print("\n"+"subject中的方法有：");  
          
        Method[] method=subject.getClass().getDeclaredMethods();
          
        for(Method m:method){  
            System.out.print(m.getName()+", ");  
        }  
          
        System.out.println("\n"+"subject的父类是："+subject.getClass().getSuperclass());  
          
        System.out.print("\n"+"subject实现的接口是：");  
          
        Class<?>[] interfaces=subject.getClass().getInterfaces();  
          
        for(Class<?> i:interfaces){  
            System.out.print(i.getName()+", ");  
        }  
  
        System.out.println("\n\n"+"运行结果为：");  
        subject.request();

        /**
         *运行结果如下：此处省略了包名，***代替
         true
         subject的Class类是：class $Proxy0
         subject中的属性有：m1, m3, m0, m2,
         subject中的方法有：request, hashCode, equals, toString,
         subject的父类是：class java.lang.reflect.Proxy
         subject实现的接口是：cn.edu.ustc.dynamicproxy.Subject,

         运行结果为：
         before calling public abstract void ***.Subject.request()
         From real subject.
         after calling public abstract void ***.Subject.request()
         */
    }

    private static void createProxyClassFile(Class c) {
        byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{c});
        //写入文件中
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("&Proxy0.class");
            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}  