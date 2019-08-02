package com.we.basics.create;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * 五种创建对象的方式
 * 1.new 最通用最常用
 * 2.Class类 的newInstance
 * 3.Constructor类，构造函数的newInstance
 * 4.clone，克隆的前提是类必须实现Cloneable，没有调用构造方法
 * 5.反序列化，需要实现serializable接口，没有调用构造方法
 */
public class ObjectCreation {
    //1.new 最通用最常用
    public static void withNew() {
        Employee emp1 = new Employee();
        emp1.setName("xiao ming1");
        System.out.println(emp1 + ", hashcode : " + emp1.hashCode());
    }

    //2.Class类 的newInstance
    public static void withClass() throws Exception{
        Employee emp1 = (Employee)Class.forName(Employee.class.getName()).newInstance();
        emp1.setName("xiao ming2");
        System.out.println(emp1 + ", hashcode : " + emp1.hashCode());
    }

    //4.clone，克隆的前提是类必须实现Cloneable，没有调用构造方法
    public static void withConstrustor() throws Exception{
        Constructor<Employee> constructor = Employee.class.getConstructor();
        Employee emp1 = constructor.newInstance();
        emp1.setName("xiao ming3");
        System.out.println(emp1 + ", hashcode : " + emp1.hashCode());
    }

    //3.Constructor类，构造函数的newInstance
    public static void withClone() throws Exception{
        Employee emp1 = new Employee();
        System.out.println("start clone------");
        Employee clone = (Employee)emp1.clone();
        clone.setName("xiao ming4");
        System.out.println(clone + ", hashcode : " + clone.hashCode());
    }

    //5.反序列化，需要实现serializable接口，没有调用构造方法
    public static void withSerializable() throws Exception{
        Employee emp4 = new Employee();

        String fileName = "data.obj";
        System.out.println("start Serializable------");
        // By using Deserialization
        // Serialization
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(emp4);
        out.close();
        //Deserialization
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
        Employee emp5 = (Employee)objectInputStream.readObject();
        objectInputStream.close();

        emp5.setName("Akash");
        System.out.println(emp5 + ", hashcode : " + emp5.hashCode());
    }


    public static void main(String[] args) throws Exception{
        withNew();
        withClass();
        withConstrustor();
        withClone();
        withSerializable();
    }

}
