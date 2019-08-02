package com.we.basics.create;

import lombok.Data;

import java.io.Serializable;

/**
 * 五种创建对象的方式
 * 1.new 最通用最常用
 * 2.Class类 的newInstance
 * 3.Constructor类，构造函数的newInstance
 * 4.clone，克隆的前提是类必须实现Cloneable，没有调用构造方法
 * 4.反序列化，需要实现serializable接口，没有调用构造方法
 */
@Data
public class Employee implements Cloneable,Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    public Employee() {
        System.out.println("Employee Constructor Called...");
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Employee [name=" + name + "]";
    }
    @Override
    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
