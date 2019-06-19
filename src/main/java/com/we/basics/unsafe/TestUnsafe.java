package com.we.basics.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 如何使用sun.misc.Unsafe完成compareAndSwapObject原子操作
 * https://www.jb51.net/article/140726.htm
 */
public class TestUnsafe {

    public static void main(String[] args) {
        Node node = new Node();
        /**
         * 通过CAS方法更新node的next属性
         * 原子操作
         */
        boolean flag = node.casNext(null,new Node());
        System.out.println(flag);
    }

    private static class Node{

        volatile Node next;

        /**
         * 使用Unsafe CAS方法
         * @param cmp 目标值与cmp比较，如果相等就更新返回true；如果不相等就不更新返回false；
         * @param val 需要更新的值；
         * @return
         */
        boolean casNext(Node cmp, Node val) {
            /**
             * compareAndSwapObject(Object var1, long var2, Object var3, Object var4)
             * var1 操作的对象
             * var2 内存偏移量
             * var3 原有值
             * var4 更新值
             */
            return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, val);
        }

        private static final sun.misc.Unsafe UNSAFE;
        private static final long nextOffset;

        static {
            try {
                UNSAFE = getUnsafe();
                Class<?> k = Node.class;
                //通过内存偏移地址修改变量值
                nextOffset = UNSAFE.objectFieldOffset
                        (k.getDeclaredField("next"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        /**
         * 获取Unsafe的方法
         * 获取了以后就可以愉快的使用CAS啦
         * @return
         */
        public static Unsafe getUnsafe() {
            try {
                Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                return (Unsafe)f.get(null);
            } catch (Exception e) {
                return null;
            }
        }
    }
}